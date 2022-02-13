package ru.barinov.jokesapplication.data.localDataBase

import androidx.room.*
import ru.barinov.jokesapplication.domain.FavoriteSavedJokeEntity

@Database(
    version = 1,
    entities = [
        FavoriteSavedJokeEntity::class
    ])
abstract class DataBase: RoomDatabase() {

    abstract fun favoriteJokesDao(): FavoriteJokesDAO
}