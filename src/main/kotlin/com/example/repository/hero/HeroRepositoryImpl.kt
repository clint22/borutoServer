package com.example.repository.hero

import com.example.models.ApiResponse
import com.example.models.Hero
import java.util.*
import kotlin.collections.HashMap

class HeroRepositoryImpl : HeroRepository {
    override val heroes: HashMap<Int, List<Hero>> by lazy { hashMapOf(1 to page1, 2 to page2) }

    companion object {
        const val NEXT_PAGE_KEY = "nextPage"
        const val PREV_PAGE_KEY = "prevPage"
    }

    override val page1: List<Hero> = listOf(
        Hero(
            id = 1,
            name = "Naruto Uzumaki",
            image = "images/naruto.jpeg",
            about = "The Show-Off, Number One Unpredictable, Noisy Ninja",
            rating = 8.0,
            power = 100,
            month = "October",
            day = "10",
            family = listOf("Minato Namikaze", "Kushina Uzumaki"),
            abilities = listOf("Lava release", "Magnet Release"),
            natureTypes = listOf("Wind release", "Lightning release")
        ),
        Hero(
            id = 2,
            name = "Boruto Uzumaki",
            image = "images/boruto.jpg",
            about = "A young ninja from Konohagakure's Uzumaki clan, and the son of Naruto Uzumaki and Hinata Hyuga.",
            rating = 7.5,
            power = 90,
            month = "March",
            day = "27",
            family = listOf("Naruto Uzumaki", "Hinata Hyuga", "Himawari Uzumaki"),
            abilities = listOf("Jōgan", "Wind release"),
            natureTypes = listOf("Wind release", "Lightning release")
        ),
        Hero(
            id = 3,
            name = "Sasuke Uchiha",
            image = "images/sasuke.jpeg",
            about = "A former member of Konohagakure's Uchiha clan and a skilled ninja with a thirst for revenge against his brother Itachi.",
            rating = 9.0,
            power = 95,
            month = "July",
            day = "23",
            family = listOf("Fugaku Uchiha", "Mikoto Uchiha", "Itachi Uchiha"),
            abilities = listOf("Sharingan", "Rinnegan"),
            natureTypes = listOf("Fire release", "Lightning release")
        )
    )
    override val page2: List<Hero> = listOf(
        Hero(
            id = 4,
            name = "Kakashi Hatake",
            image = "images/kakashi.jpeg",
            about = "A former member of the elite group of ninjas known as the ANBU and the Sixth Hokage of Konohagakure.",
            rating = 8.5,
            power = 90,
            month = "September",
            day = "15",
            family = listOf("Sakumo Hatake"),
            abilities = listOf("Sharingan", "Lightning Blade", "Chidori", "Raikiri"),
            natureTypes = listOf("Fire release", "Water release", "Earth release", "Lightning release")
        ),
        Hero(
            id = 5,
            name = "Sarada Uchiha",
            image = "images/sarada.jpeg",
            about = "The daughter of Sasuke Uchiha and Sakura Haruno, and a member of Konohagakure's Uchiha clan.",
            rating = 8.0,
            power = 85,
            month = "March",
            day = "31",
            family = listOf("Sasuke Uchiha", "Sakura Haruno"),
            abilities = listOf("Sharingan", "Chidori"),
            natureTypes = listOf("Fire release", "Lightning release")
        ),
        Hero(
            id = 6,
            name = "Obito Uchiha",
            image = "images/obito.jpeg",
            about = "A former member of Konohagakure's Uchiha clan and a former member of the Akatsuki, who adopted the identity of Tobi.",
            rating = 8.0,
            power = 90,
            month = "February",
            day = "10",
            family = listOf("Uchiha Clan"),
            abilities = listOf("Sharingan", "Kamui", "Izanagi", "Fire Release"),
            natureTypes = listOf("Fire Release", "Wind Release")
        )
    )

    override suspend fun getAllHeroes(page: Int): ApiResponse {
        return ApiResponse(
            success = true,
            message = "OK",
            prevPage = calculatePage(page)[PREV_PAGE_KEY],
            nextPage = calculatePage(page)[NEXT_PAGE_KEY],
            heroes = heroes[page]!!
        )
    }

    private fun calculatePage(page: Int): HashMap<String, Int?> {
        var prevPage: Int? = page
        var nextPage: Int? = page
        if (page in 1..4) {
            nextPage = nextPage?.plus(1)
        }
        if (page in 2..5) {
            prevPage = prevPage?.minus(1)
        }
        if (page == 1) {
            prevPage = null
        }
        if (page == 5) {
            nextPage = null
        }
        return hashMapOf(PREV_PAGE_KEY to prevPage, NEXT_PAGE_KEY to nextPage)
    }

    override suspend fun searchHeroes(name: String?): ApiResponse {
        return ApiResponse(success = true, message = "OK", heroes = findHeroes(name))
    }

    private fun findHeroes(name: String?): List<Hero> {
        val founded = mutableListOf<Hero>()
        return if (!name.isNullOrEmpty()) {
            heroes.forEach { (_, heroesList) ->
                heroesList.forEach { hero ->
                    if (hero.name.lowercase(Locale.getDefault()).contains(name.lowercase(Locale.getDefault()))) {
                        founded.add(hero)
                    }
                }
            }
            founded
        } else {
            emptyList()
        }
    }
}