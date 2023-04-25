package com.example.routes.characters

import com.example.repository.character.CharacterRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Routing.deleteCharacter() {
    val characterRepository: CharacterRepository by inject()
    delete("/boruto/characters/{id}") {
        val id = call.parameters["id"]?.toIntOrNull()
        if (id != null) {
            val updatedRows = characterRepository.removeCharacter(id)
            call.respond(message = updatedRows, status = HttpStatusCode.OK)
        } else {
            call.respond(message = "Invalid ID", status = HttpStatusCode.BadRequest)
        }
    }
}