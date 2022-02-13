package ru.barinov.jokesapplication.data.localRepository

import kotlinx.coroutines.flow.Flow
import ru.barinov.jokesapplication.domain.FavoriteSavedJokeEntity

interface LocalRepository {

    suspend fun addJoke(joke: FavoriteSavedJokeEntity)

    suspend fun deleteJoke(joke: FavoriteSavedJokeEntity)

    fun getFavoriteJokes(): Flow<List<FavoriteSavedJokeEntity>>
}