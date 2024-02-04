package com.example.repository

import com.example.dao.DatabaseSingleton.dbForRepos
import com.example.data.UserTable
import com.example.models.User
import com.example.models.UserCredentials
import com.example.security.Hashing
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction

object UserRepository{

    private val database = dbForRepos()

    fun create(user: User){
        transaction(database){
            UserTable.insert {
                it[login] = user.login
                it[firstName] = user.password
                it[lastName] = user.lastName
                it[passHash] = Hashing.getHash(user.password)
            }
        }
    }

    private fun resultRowToUser(resultRow: ResultRow) = User(login = resultRow[UserTable.login],
        firstName = resultRow[UserTable.firstName],
        lastName = resultRow[UserTable.lastName],
        password = resultRow[UserTable.passHash])

    fun readByLogin(userCred: UserCredentials) : User? = transaction(database){
            UserTable.select(UserTable.login eq userCred.login)
                .map(::resultRowToUser)
                .singleOrNull()
        }

    fun update(user: User, firstName: String? = null, lastName: String? = null, password: String? = null) : Int =
        transaction(database){
            UserTable.update({ UserTable.login eq user.login }){
                if (firstName != null) it[UserTable.firstName] = firstName
                if (lastName != null) it[UserTable.lastName] = lastName
                if (password != null) it[passHash] = Hashing.getHash(password)
            }
        }


    fun delete(user: User) : Int = transaction(database) {
        UserTable.deleteWhere { login eq user.login}
    }

    fun checkLogin(login: String) : Boolean = transaction(database) {
            UserTable.select(UserTable.login eq login).empty()
    }
}