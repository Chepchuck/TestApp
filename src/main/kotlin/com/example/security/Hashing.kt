package com.example.security

import io.ktor.util.*
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object Hashing {
    //MD2, MD5, SHA-2, SHA-224, SHA-256, SHA-384, SHA-512
    @Throws(NoSuchAlgorithmException::class)
    fun getHash(word: String, type: String = "SHA-256") : String{
        val digestedBytes = MessageDigest.getInstance(type).digest(word.toByteArray())
        return with(StringBuilder()){
            digestedBytes.forEach { b -> append(String.format("%02X", b)) }
            toString().lowercase()
        }
    }
}