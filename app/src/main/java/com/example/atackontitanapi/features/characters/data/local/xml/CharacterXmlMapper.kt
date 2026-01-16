package com.example.atackontitanapi.features.characters.data.local.xml

import com.example.atackontitanapi.features.characters.domain.Character

fun Character.toXmlModel(createdAt: Long): CharacterXmlModel {
    return CharacterXmlModel(this, createdAt)
}

fun CharacterXmlModel.toModel(): Character {
    return this.character
}
