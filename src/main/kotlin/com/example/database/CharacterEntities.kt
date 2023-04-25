package com.example.database

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

object DBCharacterTable : Table<DBCharacterEntity>("character") {
    val id = int("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val about = varchar("about").bindTo { it.about }
}

interface DBCharacterEntity : Entity<DBCharacterEntity> {
    companion object : Entity.Factory<DBCharacterEntity>()

    val id: Int
    val name: String
    val about: String
}