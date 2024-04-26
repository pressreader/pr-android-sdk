package com.newspaperdirect.example.samplesdk

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.newspaperdirect.pressreader.android.utils.extensions.EMPTY
import com.newspaperdirect.sdkfull.Dialogs.showChooseServiceDialog
import com.newspaperdirect.sdkfull.PressReader.AuthorizationError
import com.newspaperdirect.sdkfull.PressReader.Callback
import com.newspaperdirect.sdkfull.PressReader.Companion.instance
import com.newspaperdirect.sdkfull.PressReader.LogsCallback
import com.newspaperdirect.sdkfull.PressReader.ServerError
import com.newspaperdirect.sdkfull.PressReader.ServerErrorText
import com.newspaperdirect.sdkfull.catalog.Download
import com.newspaperdirect.sdkfull.catalog.Download.DownloadState
import timber.log.Timber
import java.util.Calendar

class MainActivity : AppCompatActivity(), Download.Callback {

    val TAG = "Sample MainActivity"

    private var authRetryCount = 0
    private var testIssueCid = "9xuu"
    private var testIssueDate = Calendar.getInstance().apply {
        set(Calendar.YEAR, 2023)
        set(Calendar.MONTH, 0)
        set(Calendar.DAY_OF_MONTH, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.HOUR, 12)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.time
    private var downloadState = DownloadState.Stop

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sample_main)

        findViewById<View>(R.id.button_downloaded).setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, DownloadedFragment())
                .addToBackStack("DownloadedFragment")
                .commit()
        }

        findViewById<Button>(R.id.button_download).setOnClickListener {
            if (instance.isCatalogLoaded) {
                when (downloadState) {
                    DownloadState.Stop, DownloadState.Pause ->
                        instance.catalog.getItem(testIssueCid, testIssueDate)
                            .download.start()
                    DownloadState.Progress ->
                        instance.catalog.getItem(testIssueCid, testIssueDate)
                            .download.pause()
                    DownloadState.Ready ->
                        instance.catalog.getItem(testIssueCid, testIssueDate)
                            .open()
                }
            }
        }

        findViewById<Button>(R.id.button_cancel).setOnClickListener {
            if (instance.isCatalogLoaded) {
                when (downloadState) {
                    DownloadState.Stop, DownloadState.Pause, DownloadState.Progress ->
                        instance.catalog.getItem(testIssueCid, testIssueDate)
                            .download.cancel()
                    DownloadState.Ready ->
                        instance.catalog.getItem(testIssueCid, testIssueDate)
                            .download.delete()
                }
            }
        }

        val inputToken = findViewById<EditText>(R.id.input_token)
        findViewById<View>(R.id.button_token).setOnClickListener {
            if (instance.isActivated) {
                instance.account.authorize(
                    inputToken.text.toString(),
                    object : Callback {
                        override fun onComplete(success: Boolean, throwable: Throwable?) {
                            if (success) {
                                showToast("Authorized successfully")
                            } else {
                                when (throwable) {
                                    null -> showToast("Unknown error. Should not happen")
                                    is ServerErrorText -> showToast("ServerError: " + throwable.description)
                                    is ServerError -> showToast("ServerError, code: " + throwable.code)
                                    else -> showToast("Error: " + throwable.javaClass.simpleName + " message: " + throwable.message)
                                }
                            }
                        }
                    }
                )
            }
        }

        findViewById<View>(R.id.button_logs).setOnClickListener {
            instance.getLogs(object : LogsCallback {
                override fun onComplete(
                    linkToUploadedLogs: String?,
                    additionalInfo: String?,
                    error: Throwable?
                ) {
                    showToast("$linkToUploadedLogs $additionalInfo $error")
                    Timber.tag(TAG).d("link: $linkToUploadedLogs")
                    Timber.tag(TAG).d("additional info: $additionalInfo")
                    Timber.tag(TAG).d("error: $error")
                }
            })
        }

        instance.activationErrorLiveData.observe(
            this
        ) {
            if (authRetryCount < 3) { // retry 3 times
                ++authRetryCount
                instance.requestActivation()
                instance.activationErrorLiveData.value = null
            }
        }

        instance.isCatalogLoadedLiveData.observe(
            this
        ) { addDownloadCallback() }

        instance.serviceChooserRequestLiveData.observe(
            this
        ) { serviceChooserRequest ->
            if (serviceChooserRequest != null) {
                showChooseServiceDialog(
                    this@MainActivity,
                    serviceChooserRequest.items,
                    serviceChooserRequest.callback
                )
                instance.serviceChooserRequestLiveData.value = null
            }
        }
    }

    private fun updateDownloadButton(state: DownloadState) {
        if (!isFinishing) {
            downloadState = state
            val downloadButton =
                findViewById<Button>(R.id.button_download)
            when (state) {
                DownloadState.Progress -> downloadButton.text = "Pause"
                DownloadState.Stop, DownloadState.Pause -> downloadButton.text = "Download"
                DownloadState.Ready -> downloadButton.text = "Open"
            }
        }
    }

    private fun addDownloadCallback() {
        val download: Download =
            instance.catalog.getItem(testIssueCid, testIssueDate).download
        download.addCallback(this@MainActivity)
    }

    private fun removeDownloadCallback() {
        instance.catalog.getItem(testIssueCid, testIssueDate).download.removeCallback(
            this
        )
    }

    override fun onUpdate(state: DownloadState, progress: Int, error: Throwable?) {
        val textProgress = findViewById<View>(R.id.text_download_progress) as TextView
        if (state === DownloadState.Progress || state === DownloadState.Pause) {
            textProgress.text = "$progress%"
        } else {
            textProgress.text = String.EMPTY
        }

        updateDownloadButton(state)

        when (error) {
            null -> {
            }
            is AuthorizationError -> showToast("Please authorize first.")
            is ServerErrorText -> showToast("ServerError: " + error.description)
            is ServerError -> showToast("ServerError, code: " + error.code)
            else -> showToast("Error: " + error.javaClass.simpleName + " message: " + error.message)
        }
    }

    override fun onResume() {
        super.onResume()
        addDownloadCallback()
    }

    override fun onPause() {
        super.onPause()
        removeDownloadCallback()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}
