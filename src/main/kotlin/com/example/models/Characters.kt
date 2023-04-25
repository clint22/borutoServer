package com.example.models

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Character(val id: Int, val name: String, val about: String)

object Characters : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 128)
    val about = varchar("about", 1024)

    override val primaryKey = PrimaryKey(id)
}
