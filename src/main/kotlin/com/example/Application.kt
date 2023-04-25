package com.example

import com.example.authentication.JwtConfig
import com.example.database.DatabaseFactory
import com.example.plugins.*
import io.ktor.server.application.*
import io.ktor.server.config.*

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

val databaseFactory = DatabaseFactory()

@Suppress("unused")
fun Application.module() {
    databaseFactory.init()
    configureKoin()
    configureSerialization()
    configureMonitoring()
    configureRouting()
    configureDefaultHeaders()
    configureStatusPages()
}

