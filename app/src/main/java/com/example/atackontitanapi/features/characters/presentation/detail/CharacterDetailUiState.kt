package com.example.atackontitanapi.features.characters.presentation.detail

import com.example.atackontitanapi.core.domain.ErrorApp
import com.example.atackontitanapi.features.characters.domain.Character

data class CharacterDetailUiState(
    val isLoading: Boolean = false,
    val character: Character? = null,
    val error: ErrorApp? = null
) {
    val showContent: Boolean get() = !isLoading && error == null && character != null
}
