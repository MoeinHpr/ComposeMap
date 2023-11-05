package com.hpr.core.utils

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.core.graphics.drawable.toBitmap
import coil.imageLoader
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
    return try {
        val imageLoader = this.imageLoader
        val result = imageLoader.execute(ImageRequest.Builder(this).data(url).build())
        if (result is SuccessResult) {
            val bitmap = result.drawable.toBitmap()
            val scaledBitmap = Bitmap.createScaledBitmap(bitmap , 120 , 100 , false)
            scaledBitmap.copy(Bitmap.Config.ARGB_8888 , true)
        } else null
    } catch (e: Exception) {
        Log.e("Image exception" , e.localizedMessage)
        null
    }
}

fun List<*>.hasNullValue() : Boolean{
    for (item in this){
        if (item == null)
            return true
    }
    return false
}


