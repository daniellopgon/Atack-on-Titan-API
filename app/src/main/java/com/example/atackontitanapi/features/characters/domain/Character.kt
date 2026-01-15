package com.example.atackontitanapi.features.characters.domain

import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val id: Int,
    val name: String,
    val img: String,
    val alias: List<String>,
    val species: List<String>,
    val gender: String,
    val age: Int,
    val height: String
)
