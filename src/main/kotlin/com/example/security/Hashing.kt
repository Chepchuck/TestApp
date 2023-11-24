package com.example.security

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object Hashing {
    //MD2, MD5, SHA-2, SHA-224, SHA-256, SHA-384, SHA-512
    @Throws(NoSuchAlgorithmException::class)
    fun getHash(inByte: ByteArray, type: String) : String{
        val digestedBytes = MessageDigest.getInstance(type).digest(inByte)
        return with(StringBuilder()){
            digestedBytes.forEach { b -> append(String.format("%02X", b)) }
            toString().lowercase()
        }
    }
}