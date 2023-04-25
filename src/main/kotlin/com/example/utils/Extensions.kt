package com.example.utils

import com.example.database.DBCharacterEntity
import com.example.models.Character

fun DBCharacterEntity.toCharacter(): Character {
    return Character(id, name, about)
}