package com.example.atackontitanapi.features.characters.data.remote

import com.example.atackontitanapi.core.data.remote.api.ApiClient
import com.example.atackontitanapi.core.data.remote.api.apiCall
import com.example.atackontitanapi.features.characters.data.remote.api.CharacterApiService
import com.example.atackontitanapi.features.characters.data.remote.api.toDomain
import com.example.atackontitanapi.features.characters.domain.Character
import org.koin.core.annotation.Single

@Single
class CharacterApiRemoteDataSource(
    apiClient: ApiClient
) {
    private val service = apiClient.createService(CharacterApiService::class.java)

    suspend fun fetchAll(): Result<List<Character>> = apiCall {
        service.fetchAll()
    }.map { list -> list.map { it.toDomain() } }

    suspend fun fetch(characterId: Int): Result<Character> = apiCall {
        service.fetchById(characterId)
    }.map { it.toDomain() }
}
