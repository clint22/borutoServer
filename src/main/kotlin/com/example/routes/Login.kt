package com.example.routes

import com.example.authentication.JwtConfig
import com.example.models.LoginBody
import com.example.repository.UserRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Routing.loginUser(jwtConfig: JwtConfig) {
    val userRepository: UserRepository by inject()
    post("/login") {
        val loginBody = call.receive<LoginBody>()
        val user = userRepository.getUser(loginBody.username, loginBody.password)
        if (user == null) {
            call.respond(message = "Invalid credentials", status = HttpStatusCode.Unauthorized)
            return@post
        }
        val token = jwtConfig.generateToken(JwtConfig.JwtUser(user.userId, user.username))
        call.respond(token)
    }
}