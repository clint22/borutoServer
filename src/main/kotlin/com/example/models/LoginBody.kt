package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginBody(val username: String, val password: String)