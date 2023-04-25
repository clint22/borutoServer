package com.example.database

interface DAOCharacter {
    suspend fun addNewCharacter(name: String, about: String): com.example.models.Character?
}