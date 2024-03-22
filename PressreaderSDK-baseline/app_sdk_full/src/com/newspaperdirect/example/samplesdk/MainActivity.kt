package com.newspaperdirect.example.samplesdk

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.newspaperdirect.sdk.Dialogs.showChooseServiceDialog
import com.newspaperdirect.sdk.MainActivity
import com.newspaperdirect.sdk.PressReader
import com.newspaperdirect.sdk.PressReader.Companion.instance

class MainActivity : AppCompatActivity() {
    val TAG = "Sample MainActivity"
    private var authRetryCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sample_main)

        findViewById<View>(R.id.button_start).setOnClickListener {
            val intent = Intent(this@MainActivity, MainActivity::class.java)
            startActivity(intent)
        }

        findViewById<View>(R.id.button_logs).setOnClickListener {
            instance.getLogs(object : PressReader.LogsCallback {
                override fun onComplete(
                    linkToUploadedLogs: String?,
                    additionalInfo: String?,
                    error: Throwable?
                ) {
                    showToast("$linkToUploadedLogs $additionalInfo $error")
                    Log.d(TAG, "link: $linkToUploadedLogs")
                    Log.d(TAG, "additional info: $additionalInfo")
                    Log.d(TAG, "error: $error")
                }
            })
        }

        instance.activationErrorLiveData.observe(this) {
            if (authRetryCount < 3) { // retry 3 times
                ++authRetryCount
                instance.requestActivation()
                instance.activationErrorLiveData.value = null
            }
        }
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

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}
