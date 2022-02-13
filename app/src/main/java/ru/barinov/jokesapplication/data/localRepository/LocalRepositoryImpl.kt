package ru.barinov.jokesapplication.data.localRepository

import kotlinx.coroutines.flow.Flow
import ru.barinov.jokesapplication.data.localDataBase.FavoriteJokesDAO
import ru.barinov.jokesapplication.domain.*

class LocalRepositoryImpl(
    val dao: FavoriteJokesDAO
)
    : LocalRepository {

    override suspend fun addJoke(joke: FavoriteSavedJokeEntity) {
        dao.addJoke(joke)
    }

    override suspend fun deleteJoke(joke: FavoriteSavedJokeEntity) {
        dao.deleteJoke(joke)
    }

    override fun getFavoriteJokes(): Flow<List<FavoriteSavedJokeEntity>> {
       return dao.getAllJokes()
    }

}