package com.sky.account.manager.util

import java.io.File

/**
 * Created by sky on 17-9-4.
 */
object FileUtil {

    fun mkdirs(path: String) {
        val file = File(path)
        if (!file.exists()) file.mkdirs()
    }
}