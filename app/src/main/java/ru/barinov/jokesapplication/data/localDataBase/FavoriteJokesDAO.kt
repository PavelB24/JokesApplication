package ru.barinov.jokesapplication.data.localDataBase

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.barinov.jokesapplication.domain.FavoriteSavedJokeEntity

@Dao
interface FavoriteJokesDAO {

    @Query("SELECT * FROM favorite_jokes")
    fun getAllJokes(): Flow<List<FavoriteSavedJokeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addJoke(joke: FavoriteSavedJokeEntity)

    @Delete
    suspend fun deleteJoke(joke: FavoriteSavedJokeEntity )

}