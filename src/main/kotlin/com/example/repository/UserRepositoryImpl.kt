package com.example.repository

class UserRepositoryImpl : UserRepository {

    private val credentialsToUsers =
        mapOf(
            "admin:admin" to UserRepository.User(1, "admin"),
            "clint:1234" to UserRepository.User(2, "clint")
        )

    override fun getUser(username: String, password: String): UserRepository.User? {
        return credentialsToUsers["$username:$password"]
    }
}