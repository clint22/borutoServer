package com.example.routes.characters

import com.example.models.Character
import com.example.repository.character.CharacterRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Routing.addCharacter() {
    val characterRepository: CharacterRepository by inject()
    post("/boruto/addCharacter") {
        val character = call.receive<Character>()
        val createdId = characterRepository.addCharacter(character)
        call.respond(message = "Item created with $createdId", status = HttpStatusCode.Created)
    }
}