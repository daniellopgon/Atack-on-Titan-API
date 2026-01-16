package com.example.atackontitanapi.features.characters.data.remote.api

import com.example.atackontitanapi.features.characters.domain.Character

fun CharacterApiModel.toDomain(): Character {
    return Character(
        id = id,
        name = name,
        img = img,
        alias = alias,
        species = species,
        gender = gender,
        age = age,
        height = height
    )
}
