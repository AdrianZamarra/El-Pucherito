package com.ttt.elpucherito.util

import android.content.Context
import java.io.IOException
import java.io.InputStream

fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    var json: String? = null
    try{
        val inputStream: InputStream = context.assets.open(fileName);
        json = inputStream.bufferedReader().use { it.readText() }

    } catch (e :IOException){
        e.printStackTrace()
        return null
    }

    return json
}
