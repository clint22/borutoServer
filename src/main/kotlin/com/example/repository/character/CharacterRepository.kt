package com.example.repository.character

import com.example.models.Character

interface CharacterRepository {
    suspend fun getCharacters(): List<Character>
    suspend fun addCharacter(character: Character): Character
    suspend fun getCharacterById(id: Int): Character?
    suspend fun updateCharacter(id: Int, character: Character): Boolean
    suspend fun removeCharacter(id: Int): Boolean
}