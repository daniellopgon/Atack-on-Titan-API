package com.example.atackontitanapi.features.characters.data.remote.api

import com.example.atackontitanapi.features.characters.domain.Character

fun CharacterApiModel.toDomain(): Character {
    val ageStr = when (age) {
        is Number -> age.toString()
        is String -> age
        else -> "Unknown"
    }
    
    return Character(
        id = id,
        name = name,
        img = img ?: "",
        alias = alias,
        species = species,
        gender = gender,
        age = ageStr,
        height = height
    )
}
