package com.example.routes.heroes

import com.example.models.ApiResponse
import com.example.repository.hero.HeroRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Route.searchHeroes() {
    val heroRepository: HeroRepository by inject()
    get("/boruto/heroes/search") {
        try {
            val name = call.request.queryParameters["name"]
            val apiResponse = heroRepository.searchHeroes(name)
            call.respond(message = apiResponse, status = HttpStatusCode.OK)
        } catch (e: IllegalArgumentException) {
            call.respond(
                message = ApiResponse(message = "Please pass a string argument", success = false),
                status = HttpStatusCode.BadRequest
            )
        }
    }
}