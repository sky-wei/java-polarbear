package com.sky.account.manager.util

import org.junit.Test

import org.junit.Assert.*
import java.io.File

/**
 * Created by sky on 17-9-4.
 */
class MD5UtilTest {

    @Test
    fun bytesToHexString() {

    }

    @Test
    fun hexStringToBytes() {

    }

    @Test
    fun md5sum() {

    }

    @Test
    fun md5sum1() {

    }

    @Test
    fun md5sum2() {

        println(">>>>>>>>>>>>>>>>>>>> ${MD5Util.md5sum(File("/home/sky/Desktop/Android命名规范.tar.gz"))}")
    }
}