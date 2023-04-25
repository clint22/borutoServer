package com.example.plugins

import com.example.routes.*
import com.example.routes.characters.*
import com.example.routes.heroes.getAllHeroes
import com.example.routes.heroes.searchHeroes
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    val jwtConfig = configureAuthentication()
    routing {
        root()
        getAllHeroes()
        searchHeroes()
        getCharacters()
        getCharactersById()
        addCharacter()
        updateCharacter()
        deleteCharacter()
        loginUser(jwtConfig)
        static("/images") {
            resources("images")
        }
    }
}
