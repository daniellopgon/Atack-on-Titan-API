package com.example.atackontitanapi.features.characters.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterApiService {

    @GET("characters")
    suspend fun fetchAll(): Response<CharacterApiResponse>

    @GET("characters/{id}")
    suspend fun fetchById(@Path("id") id: Int): Response<CharacterApiModel>
}
