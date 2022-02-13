package ru.barinov.jokesapplication.ui

import android.widget.ImageButton

interface JokeItemClickListener {

    fun onFavoriteButtonPressed(item: RecyclerViewItemModel, action: (Boolean) -> Unit)

}
