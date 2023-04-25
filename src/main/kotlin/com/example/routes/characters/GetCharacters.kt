package com.example.routes.characters

import com.example.repository.character.CharacterRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Routing.getCharacters() {
    val characterRepository: CharacterRepository by inject()
    authenticate {
        get("/boruto/characters") {
            try {
                val apiResponse = characterRepository.getCharacters()
                call.respond(message = apiResponse, status = HttpStatusCode.OK)

            } catch (e: Exception) {
                call.respond(message = "Sorry some exception occurred", status = HttpStatusCode.BadRequest)
            }
        }
    }
}