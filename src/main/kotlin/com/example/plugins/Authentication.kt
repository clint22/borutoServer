package com.example.plugins

import com.example.authentication.JwtConfig
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.configureAuthentication(): JwtConfig {
    val jwtSecret = environment.config.property("ktor.jwt.secret").getString()
    val jwtConfig = JwtConfig(jwtSecret)
    install(Authentication) {
        jwt {
            jwtConfig.configureKtorFeature(this)
        }
    }
    return jwtConfig
}