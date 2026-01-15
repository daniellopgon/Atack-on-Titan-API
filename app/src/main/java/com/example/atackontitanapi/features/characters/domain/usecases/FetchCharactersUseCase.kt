package com.example.atackontitanapi.features.characters.domain.usecases

import org.koin.core.annotation.Single
import com.example.atackontitanapi.features.characters.domain.Character
import com.example.atackontitanapi.features.characters.domain.CharacterRepository

@Single
class FetchCharactersUseCase(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(): Result<List<Character>> {
        return repository.findAll()
    }
}
