package com.example.atackontitanapi.features.characters.data

import com.example.atackontitanapi.features.characters.data.local.xml.CharacterXmlLocalDataSource
import com.example.atackontitanapi.features.characters.data.remote.CharacterApiRemoteDataSource
import com.example.atackontitanapi.features.characters.domain.Character
import com.example.atackontitanapi.features.characters.domain.CharacterRepository
import org.koin.core.annotation.Single

@Single(binds = [CharacterRepository::class])
class CharacterDataRepository(
    private val remoteDataSource: CharacterApiRemoteDataSource,
    private val localDataSource: CharacterXmlLocalDataSource
) : CharacterRepository {

    override suspend fun findAll(): Result<List<Character>> {
        val localResult = localDataSource.findAll()
        val cachedCharacters = localResult.getOrDefault(emptyList())

        return if (cachedCharacters.isEmpty()) {
            remoteDataSource.fetchAll().onSuccess { characters ->
                localDataSource.clear()
                localDataSource.save(characters)
            }
        } else {
            localResult
        }
    }

    override suspend fun findById(id: Int): Result<Character> {
        localDataSource.find(id).getOrNull()?.let { character ->
            return Result.success(character)
        }

        return remoteDataSource.fetch(id).onSuccess { character ->
            localDataSource.save(character)
        }
    }
}
