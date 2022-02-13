package ru.barinov.jokesapplication.domain

import ru.barinov.jokesapplication.ui.*

open class LoadedJokeEntity(
    val id: Int,
    val joke: String
)

fun LoadedJokeEntity.toFavoriteSavedJoke(currentTime: Long): FavoriteSavedJokeEntity {
    return FavoriteSavedJokeEntity(this.id, this.joke, currentTime)
}

fun LoadedJokeEntity.toRecyclerViewItemModel(
    listener: JokeItemClickListener, currentTime: Long
): RecyclerViewItemModel {
    return RecyclerViewItemModel(this.id, this.joke, false, listener, currentTime)
}