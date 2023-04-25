package com.example.routes.characters

import com.example.repository.character.CharacterRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Routing.getCharactersById() {
    val characterRepository: CharacterRepository by inject()
    get("/boruto/characters/{id}") {
        val id = call.parameters["id"]?.toIntOrNull()
        if (id != null) {
            val apiResponse = characterRepository.getCharacterById(id)
            if (apiResponse != null) {
                call.respond(message = apiResponse, status = HttpStatusCode.OK)
            } else {
                call.respond(message = "Item not found", status = HttpStatusCode.NotFound)
            }
        } else {
            call.respond(message = "Invalid ID", status = HttpStatusCode.BadRequest)
        }
    }
}