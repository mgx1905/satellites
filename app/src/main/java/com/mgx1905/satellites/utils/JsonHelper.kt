package com.mgx1905.satellites.utils

import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

/**
 * Created by mgx1905 on 26.12.2021
 */

object JsonHelper {
    fun readFromAsset(filename: String): String? {
        val json = try {
            val input: InputStream = context.assets.open("${filename}.json")
            val size: Int = input.available()
            val buffer = ByteArray(size)
            input.read(buffer)
            input.close()
            val charset = Charset.forName("UTF-8")
            String(buffer, charset)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }
}