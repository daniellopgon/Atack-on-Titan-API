package com.example.atackontitanapi.features.characters.domain

interface CharacterRepository {
    suspend fun findAll(): Result<List<Character>>
    suspend fun findById(id: Int): Result<Character>
}
