package com.sky.account.manager.util

import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec


/**
 * Created by sky on 17-9-4.
 */
object SecretUtil {

    private val A = byteArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)

    fun buildKey(password: String): SecretKey {

        val random = SecureRandom.getInstance("SHA1PRNG")
        random.setSeed(password.toByteArray())

        val keyGenerator = KeyGenerator.getInstance("AES")
        keyGenerator.init(128, random)
        return keyGenerator.generateKey()
    }

    fun decrypt(key: SecretKey, value: ByteArray): ByteArray {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, key, IvParameterSpec(A))
        return cipher.doFinal(value)
    }

    fun encrypt(key: SecretKey, value: ByteArray): ByteArray {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, key, IvParameterSpec(A))
        return cipher.doFinal(value)
    }

    fun encrypt(password: String, value: String): String {
        val encryptValue = encrypt(buildKey(password), value.toByteArray())
        return MD5Util.bytesToHexString(encryptValue)
    }

    fun decrypt(password: String, value: String): String {
        val decryptValue = decrypt(buildKey(password), MD5Util.hexStringToBytes(value))
        return String(decryptValue)
    }
}