package com.example.database

import com.example.models.Characters
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DAOCharacterImpl : DAOCharacter, KoinComponent {

    private val dbProvider by inject<DatabaseProviderContract>()

    private fun resultRowToCharacter(row: ResultRow) =
        com.example.models.Character(
            name = row[Characters.name],
            about = row[Characters.about],
            id = row[Characters.id]
        )

    override suspend fun addNewCharacter(name: String, about: String): com.example.models.Character? =
        dbProvider.dbQuery {
            val insertCharacter = Characters.insert {
                it[Characters.name] = name
                it[Characters.about] = about
            }
            insertCharacter.resultedValues?.singleOrNull()?.let(::resultRowToCharacter)
        }
}