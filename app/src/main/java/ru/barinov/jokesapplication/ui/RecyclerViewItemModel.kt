package ru.barinov.jokesapplication.ui

import ru.barinov.jokesapplication.domain.*

data class RecyclerViewItemModel(
  val id: Int,
  val joke: String,
  var isFavorite: Boolean,
  val listener: JokeItemClickListener,
  val creationDate: Long
 )

fun RecyclerViewItemModel.toFavoriteSavedJoke(): FavoriteSavedJokeEntity {
 return FavoriteSavedJokeEntity(this.id, this.joke, this.creationDate)
}

