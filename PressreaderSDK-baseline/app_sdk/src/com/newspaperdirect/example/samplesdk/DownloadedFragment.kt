package com.newspaperdirect.example.samplesdk

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.newspaperdirect.pressreader.android.utils.extensions.EMPTY
import com.newspaperdirect.sdkfull.PressReader
import com.newspaperdirect.sdkfull.catalog.Download
import com.newspaperdirect.sdkfull.catalog.Item
import java.text.SimpleDateFormat
import java.util.Locale

class DownloadedFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.sample_content, container, false)

        PressReader.instance.activationErrorLiveData.observe(
            viewLifecycleOwner
        ) {
            updateView(view)
        }

        PressReader.instance.isCatalogLoadedLiveData.observe(
            viewLifecycleOwner
        ) {
            updateView(view)
        }

        return view
    }

    private fun updateView(view: View) {
        val recyclerView: RecyclerView = view.findViewById(R.id.sample_recycler_view)
        val progressBar = view.findViewById<ProgressBar>(R.id.progress)
        val errorTextView = view.findViewById<TextView>(R.id.text_error)
        val showData = PressReader.instance.isCatalogLoaded
        val showError = !showData && PressReader.instance.activationError != null
        val showLoading = !showData && !showError
        recyclerView.visibility = if (showData) View.VISIBLE else View.GONE
        progressBar.visibility = if (showLoading) View.VISIBLE else View.GONE
        errorTextView.visibility = if (showError) View.VISIBLE else View.GONE
        if (showData) {
            load(recyclerView)
        } else if (showError) {
            errorTextView.text = PressReader.instance.activationError?.message
        }
    }

    private fun load(recyclerView: RecyclerView) {
        val data = PressReader.instance.catalog.downloaded.items

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = object : RecyclerView.Adapter<ViewHolder>() {
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int
            ): ViewHolder {
                return ViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(R.layout.sample_content_cell, parent, false)
                )
            }

            override fun onBindViewHolder(
                holder: ViewHolder,
                position: Int
            ) {
                val item = data[position]
                val formattedDate = SimpleDateFormat("E, d MMM yyyy", Locale.getDefault()).format(item.date)

                holder.textTitle.text = item.title
                holder.textDate.text = formattedDate
                holder.textDownloadProgress.text = String.EMPTY
                holder.buttonDelete.setOnClickListener {
                    item.delete()
                }

                holder.item = item
                item.download.addCallback(holder)

                holder.itemView.setOnClickListener {
                    if (!item.open()) {
                        (activity as MainActivity).showToast("Cannot open")
                    }
                }
            }

            override fun onViewRecycled(holder: ViewHolder) {
                super.onViewRecycled(holder)

                holder.item?.download?.removeCallback(holder)
                holder.item = null
            }

            override fun getItemCount(): Int {
                return data.size
            }
        }
    }

    override fun onResume() {
        super.onResume()
        PressReader.instance.catalog.downloaded.addCallback(this::onUpdate)
    }

    override fun onPause() {
        super.onPause()
        PressReader.instance.catalog.downloaded.removeCallback(this::onUpdate)
    }

    private fun onUpdate() {
        view?.let {
            updateView(it)
        }
    }

    internal inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), Download.Callback {
        var textTitle: TextView
        var textDate: TextView
        var textDownloadProgress: TextView
        var buttonDelete: Button
        var item: Item? = null

        init {
            textTitle = itemView.findViewById(R.id.text_title)
            textDate = itemView.findViewById(R.id.text_date)
            textDownloadProgress = itemView.findViewById(R.id.text_download_progress)
            buttonDelete = itemView.findViewById(R.id.button_delete)
        }

        override fun onUpdate(state: Download.DownloadState, progress: Int, error: Throwable?) {
            textDownloadProgress.text = "$progress%"
        }
    }
}
