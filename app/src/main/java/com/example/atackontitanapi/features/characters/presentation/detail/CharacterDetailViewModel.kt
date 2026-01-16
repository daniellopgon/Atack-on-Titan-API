package com.example.atackontitanapi.features.characters.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atackontitanapi.core.domain.ErrorApp
import com.example.atackontitanapi.features.characters.domain.usecases.GetCharacterByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CharacterDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase
) : ViewModel() {

    private val characterId: Int = savedStateHandle["characterId"] ?: -1

    private val _uiState = MutableStateFlow(CharacterDetailUiState())
    val uiState: StateFlow<CharacterDetailUiState> = _uiState.asStateFlow()

    init {
        loadCharacter()
    }

    fun loadCharacter() {
        if (characterId == -1) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            getCharacterByIdUseCase(characterId).fold(
                onSuccess = { character ->
                    _uiState.update { it.copy(
                        isLoading = false,
                        character = character
                    )}
                },
                onFailure = { error ->
                    _uiState.update { it.copy(
                        isLoading = false,
                        error = error as? ErrorApp
                    )}
                }
            )
        }
    }

    fun retry() {
        loadCharacter()
    }
}
