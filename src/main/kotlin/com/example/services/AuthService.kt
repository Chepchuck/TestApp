package com.example.services

import com.example.models.User
import com.example.models.UserCredentials
import com.example.models.UserSession
import com.example.repository.UserRepository
import com.example.security.Hashing
import com.example.utils.TokenManager

class AuthService {

    fun reg(user: User) : Boolean{
        if (!UserRepository.checkLogin(user.login)){
            UserRepository.create(user)
            return true
        }else{
            return false
        }
    }

    fun login(userCredentials: UserCredentials) : Boolean {
        val user = UserRepository.readByLogin(userCredentials)
        return user != null && Hashing.getHash(userCredentials.password) == user.password
    }

    fun logout(tokenManager: TokenManager, userSession: UserSession){
        tokenManager.clearToken(userSession)
    }
}