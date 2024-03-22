package com.newspaperdirect.example.samplesdk

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.model.stream.HttpGlideUrlLoader
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.RequestOptions

@GlideModule
class SampleAppGlideModule : AppGlideModule() {
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setDefaultRequestOptions(RequestOptions().set(HttpGlideUrlLoader.TIMEOUT, 30000))
        super.applyOptions(context, builder)
    }
}
