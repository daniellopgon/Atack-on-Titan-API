package com.example.atackontitanapi.features.characters.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atackontitanapi.core.domain.ErrorApp
import com.example.atackontitanapi.features.characters.domain.usecases.FetchCharactersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CharacterListViewModel(
    private val fetchCharactersUseCase: FetchCharactersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(CharacterListUiState())
    val uiState: StateFlow<CharacterListUiState> = _uiState.asStateFlow()

    init {
        loadCharacters()
    }

    fun loadCharacters() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            fetchCharactersUseCase().fold(
                onSuccess = { characters ->
                    _uiState.update { it.copy(
                        isLoading = false,
                        characters = characters
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
        loadCharacters()
    }
}
