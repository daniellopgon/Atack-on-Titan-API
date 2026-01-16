package com.example.atackontitanapi.features.characters.data.local.xml

import com.example.atackontitanapi.core.data.local.xml.XmlModel
import com.example.atackontitanapi.features.characters.domain.Character
import kotlinx.serialization.Serializable

@Serializable
data class CharacterXmlModel(
    val character: Character,
    val createdAt: Long
) : XmlModel {
    override fun getId(): String = character.id.toString()
    override fun getPersistedTime(): Long = createdAt
}
