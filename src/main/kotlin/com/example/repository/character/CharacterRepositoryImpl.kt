package com.example.repository.character

import com.example.database.DatabaseManager
import com.example.models.Character
import com.example.utils.toCharacter

class CharacterRepositoryImpl : CharacterRepository {
    private val databaseManager = DatabaseManager()

    override suspend fun getCharacters(): List<Character> {
        return databaseManager.getAllCharacters().map { Character(it.id, it.name, it.about) }
    }

    override suspend fun addCharacter(character: Character): Character {
        return databaseManager.addCharacter(character)
    }

    override suspend fun getCharacterById(id: Int): Character? {
        return databaseManager.getCharacterById(id)?.toCharacter()
    }

    override suspend fun updateCharacter(id: Int, character: Character): Boolean {
        return databaseManager.updateCharacter(id, character)
    }

    override suspend fun removeCharacter(id: Int): Boolean {
        return databaseManager.removeCharacter(id)
    }
}