package com.example.atackontitanapi.features.characters.data.remote.api

import com.google.gson.annotations.SerializedName

data class CharacterApiResponse(
    @SerializedName("info") val info: ApiInfo,
    @SerializedName("results") val results: List<CharacterApiModel>
)

data class ApiInfo(
    @SerializedName("count") val count: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("next_page") val nextPage: String?,
    @SerializedName("prev_page") val prevPage: String?
)

data class CharacterApiModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("img") val img: String?,
    @SerializedName("alias") val alias: List<String>,
    @SerializedName("species") val species: List<String>,
    @SerializedName("gender") val gender: String,
    @SerializedName("age") val age: Any, // Can be Int or String "unknown"
    @SerializedName("height") val height: String
)
