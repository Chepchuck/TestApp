package com.example.security

import io.ktor.util.*

val digestFunction = getDigestFunction("SHA-256") {"ktor${it.length}"}