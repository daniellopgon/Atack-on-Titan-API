package com.example.atackontitanapi.features.characters.data.local.xml

import com.example.atackontitanapi.core.data.local.xml.XmlCacheStorage
import com.example.atackontitanapi.core.domain.ErrorApp
import com.example.atackontitanapi.features.characters.domain.Character
import org.koin.core.annotation.Single

@Single
class CharacterXmlLocalDataSource(
    private val xmlCacheStorage: XmlCacheStorage<CharacterXmlModel>
) {
    companion object {
        private const val CACHE_EXPIRATION_MS = 5 * 60 * 1000L // 5 minutes
    }

    fun findAll(): Result<List<Character>> {
        return xmlCacheStorage.obtainAll().fold(
            onSuccess = { models ->
                val characters = models
                    .filter { isValid(it) }
                    .map { it.toModel() }
                Result.success(characters)
            },
            onFailure = { Result.failure(ErrorApp.CacheError) }
        )
    }

    fun find(characterId: Int): Result<Character?> {
        return xmlCacheStorage.obtain(characterId.toString()).fold(
            onSuccess = { model ->
                if (model != null && isValid(model)) {
                    Result.success(model.character)
                } else {
                    Result.success(null)
                }
            },
            onFailure = { Result.failure(ErrorApp.CacheError) }
        )
    }

    fun save(characters: List<Character>): Result<Boolean> {
        val createdAt = System.currentTimeMillis()
        return characters.map { character ->
            xmlCacheStorage.save(character.toXmlModel(createdAt))
        }.lastOrNull() ?: Result.success(true)
    }

    fun save(character: Character): Result<Boolean> {
        val createdAt = System.currentTimeMillis()
        return xmlCacheStorage.save(character.toXmlModel(createdAt))
    }

    fun clear() = xmlCacheStorage.clear()

    private fun isValid(model: CharacterXmlModel): Boolean {
        val now = System.currentTimeMillis()
        return (now - model.getPersistedTime()) < CACHE_EXPIRATION_MS
    }
}
