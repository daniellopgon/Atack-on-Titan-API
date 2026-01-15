package com.example.atackontitanapi.features.characters.domain.usecases

import org.koin.core.annotation.Single
import com.example.atackontitanapi.features.characters.domain.Character
import com.example.atackontitanapi.features.characters.domain.CharacterRepository

@Single
class GetCharacterByIdUseCase(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(id: Int): Result<Character> {
        return repository.findById(id)
    }
}
