package com.example.data

import org.jetbrains.exposed.dao.id.IntIdTable

object ChatTable : IntIdTable(){
    val name = varchar("name", 20)
}