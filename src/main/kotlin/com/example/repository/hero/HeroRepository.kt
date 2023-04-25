package com.example.repository.hero

import com.example.models.ApiResponse
import com.example.models.Hero

interface HeroRepository {

    val heroes: HashMap<Int, List<Hero>>

    val page1: List<Hero>
    val page2: List<Hero>


    suspend fun getAllHeroes(page: Int = 1): ApiResponse

    suspend fun searchHeroes(name: String?): ApiResponse
}