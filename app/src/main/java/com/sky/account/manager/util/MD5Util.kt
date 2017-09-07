/*
 * Copyright (c) 2017. The sky Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sky.account.manager.util

import java.io.File
import java.security.MessageDigest


/**
 * Created by starrysky on 16-8-2.
 */
object MD5Util {

    private val TAG = "MD5Util"

    /**
     * Convert byte[] to hex string
     * @param src byte[] data
     * @return hex string
     */
    fun bytesToHexString(src: ByteArray): String {

        val builder = StringBuilder()

        for (i in src.indices) {

            val v = 0xFF and src[i].toInt()
            val hv = Integer.toHexString(v)

            if (hv.length < 2) {
                builder.append(0)
            }
            builder.append(hv)
        }
        return builder.toString().toUpperCase()
    }

    /**
     * Convert hex string to byte[]
     * @param string the hex string
     * @return byte[]
     */
    fun hexStringToBytes(string: String): ByteArray {

        var hexString = string.toUpperCase()

        val length = hexString.length / 2
        val hexChars = hexString.toCharArray()
        val bytes = ByteArray(length)

        for (i in 0 until length) {
            val pos = i * 2
            bytes[i] = (charToByte(hexChars[pos]).toInt() shl 4 or charToByte(hexChars[pos + 1]).toInt()).toByte()
        }
        return bytes
    }

    /**
     * Convert char to byte
     * @param c char
     * @return byte
     */
    private fun charToByte(c: Char): Byte {
        return "0123456789ABCDEF".indexOf(c.toString()).toByte()
    }

    fun md5sum(value: String, charsetName: String = "UTF-8"): String {
        try {
            return md5sum(value.toByteArray(charset(charsetName)))
        } catch (e: Exception) {
            Log.e(TAG, "字符串编码异常", e)
        }
        return ""
    }

    fun md5sum(value: ByteArray): String {

        try {
            // 计算MD5值信息
            val messageDigest = MessageDigest.getInstance("MD5")

            messageDigest.reset()
            messageDigest.update(value)

            return bytesToHexString(messageDigest.digest())
        } catch (e: Exception) {
            Log.e(TAG, "处理MD5异常", e)
        }
        return ""
    }

    fun md5sum(file: File): String {

        try {
            // 计算MD5值信息
            val digest = MessageDigest.getInstance("MD5")
            digest.reset()

            file.inputStream().use {

                val buffer = ByteArray(1024)
                var length: Int

                while (true) {

                    length = it.read(buffer)

                    if (length == -1) break

                    digest.update(buffer, 0, length)
                }
            }

            return bytesToHexString(digest.digest())
        } catch (e: Exception) {
            Log.e(TAG, "处理MD5异常", e)
        }
        return ""
    }
}
