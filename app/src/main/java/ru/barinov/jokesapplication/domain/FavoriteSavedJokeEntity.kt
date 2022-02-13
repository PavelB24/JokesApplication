package ru.barinov.jokesapplication.domain

import androidx.room.*
import ru.barinov.jokesapplication.ui.*
import ru.barinov.jokesapplication.ui.uiModels.RecyclerViewItemModel

@Entity(tableName = "favorite_jokes")
data class FavoriteSavedJokeEntity(
    @PrimaryKey
    val id: Int,
    val joke: String,
    @ColumnInfo(name = "creation_time")
    val creationTime: Long
)

fun FavoriteSavedJokeEntity.toRecyclerViewItemModel(listener: JokeItemClickListener): RecyclerViewItemModel {
    return RecyclerViewItemModel(this.id, this.joke,  true, listener, this.creationTime )
}