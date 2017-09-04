package com.sky.account.manager.util

import org.junit.Test

import org.junit.Assert.*

/**
 * Created by sky on 17-9-4.
 */
class SecretUtilTest {

    @Test
    fun encrypt() {

        val t1 = SecretUtil.encrypt(SecretUtil.buildKey("123456"), "123".toByteArray())

        println(">>>>>>>>>>>>>>>>> t1 ${MD5Util.bytesToHexString(t1)}")

        val t2 = SecretUtil.decrypt(SecretUtil.buildKey("123456"), t1)

        println(">>>>>>>>>>>>>>>>> t2 ${String(t2)}")


//        val value = MD5Util.bytesToHexString("jingcai1314".toByteArray())
//        println(">>>>>>>>>>>>>> $value")
//        println(">>>>>>>>>>>>>> ${String(MD5Util.hexStringToBytes(value))}")
//
//        val  encryptValue = SecretUtil.encrypt("123", "jingcai1314")
//
//        println(">>>>>>>>>>>>>>>> $encryptValue")
//
//        print(">>>>>>>>>>>>>>>>> ${SecretUtil.decrypt("123", encryptValue)}")
    }
}