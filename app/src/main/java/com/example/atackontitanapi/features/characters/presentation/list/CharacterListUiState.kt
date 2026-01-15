package com.example.atackontitanapi.features.characters.presentation.list

import com.example.atackontitanapi.core.domain.ErrorApp
import com.example.atackontitanapi.features.characters.domain.Character

data class CharacterListUiState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val error: ErrorApp? = null
) {
    val showContent: Boolean get() = !isLoading && error == null && characters.isNotEmpty()
    val showEmpty: Boolean get() = !isLoading && error == null && characters.isEmpty()
}
