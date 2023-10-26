package com.hpr.core.utils

import android.content.Context
import android.graphics.Bitmap
import androidx.core.graphics.drawable.toBitmap
import coil.request.ImageRequest
import coil.request.SuccessResult
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


suspend fun Context.loadImageBitmap(id : String ,imageUrl: String, mBitmap: (Bitmap , String) -> Unit) {
    var image: Bitmap?
    withContext(Dispatchers.IO) {
        image = getBitmapFromUrl(imageUrl)
        image?.let {
            mBitmap.invoke(it , id)
        }
    }
}

suspend fun Context.getBitmapFromUrl(url: String): Bitmap? {
    try {
        val entryPoint =
            EntryPointAccessors.fromApplication(this, ImageLoaderEntryPoint::class.java)
        val imageLoader = entryPoint.imageLoader()
        val result = imageLoader.execute(ImageRequest.Builder(this).data(url).build())
        return if (result is SuccessResult) {
             val bitmap = result.drawable.toBitmap()
            val scaledBitmap = Bitmap.createScaledBitmap(bitmap , 100 , 80 , false)
            scaledBitmap.copy(Bitmap.Config.ARGB_8888 , true)
        } else null
    } catch (e: Exception) {
        throw IllegalStateException("Failed to load image")
    }
}

fun List<*>.hasNullValue() : Boolean{
    for (item in this){
        if (item == null)
            return true
    }
    return false
}


