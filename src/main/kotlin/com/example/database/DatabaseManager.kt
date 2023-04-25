package com.example.database

import com.example.models.Character
import org.ktorm.database.Database
import org.ktorm.dsl.*
import org.ktorm.entity.firstOrNull
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toList

class DatabaseManager {

    //    config
    private val hostname = "localhost"
    private val databaseName = "Boruto"
    private val username = "root"
    private val password = "thisissql"

    //    database
    private val ktormDatabase: Database

    init {
        val jdbcUrl = "jdbc:mysql://$hostname:3306/$databaseName?user=$username&password=$password&useSSL=false"
        ktormDatabase = Database.connect(jdbcUrl)
    }

    fun getAllCharacters(): List<DBCharacterEntity> {
        return ktormDatabase.sequenceOf(DBCharacterTable).toList()
    }

    fun getCharacterById(id: Int): DBCharacterEntity? {
        return ktormDatabase.sequenceOf(DBCharacterTable).firstOrNull { it.id eq id }
    }

    fun addCharacter(character: Character): Character {
        val insertedId = ktormDatabase.insertAndGenerateKey(DBCharacterTable) {
            set(DBCharacterTable.name, character.name)
            set(DBCharacterTable.about, character.about)
        } as Int
        return Character(insertedId, character.name, character.about)
    }

    fun updateCharacter(id: Int, character: Character): Boolean {
        val updatedRows = ktormDatabase.update(DBCharacterTable) {
            set(DBCharacterTable.name, character.name)
            set(DBCharacterTable.about, character.about)
            where {
                it.id eq id
            }
        }
        return updatedRows > 0
    }

    fun removeCharacter(id: Int): Boolean {
        val deletedRows = ktormDatabase.delete(DBCharacterTable) {
            it.id eq id
        }
        return deletedRows > 0
    }
}