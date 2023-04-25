package com.example.routes.characters

import com.example.models.Character
import com.example.repository.character.CharacterRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Routing.updateCharacter() {
    val characterRepository: CharacterRepository by inject()
    put("/boruto/characters/{id}") {
        val id = call.parameters["id"]?.toIntOrNull()
        val character = call.receive<Character>()

        if (id != null) {
            val updatedRows = characterRepository.updateCharacter(id, character)
            call.respond(message = updatedRows, status = HttpStatusCode.OK)
        } else {
            call.respond(message = "Invalid ID", status = HttpStatusCode.BadRequest)
        }
    }
}