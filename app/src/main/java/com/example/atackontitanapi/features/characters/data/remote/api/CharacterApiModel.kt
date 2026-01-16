package com.example.atackontitanapi.features.characters.data.remote.api

import com.google.gson.annotations.SerializedName

data class CharacterApiModel(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("img") val img: String,
    @SerializedName("alias") val alias: List<String>,
    @SerializedName("species") val species: List<String>,
    @SerializedName("gender") val gender: String,
    @SerializedName("age") val age: Int,
    @SerializedName("height") val height: String
)
