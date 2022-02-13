package ru.barinov.jokesapplication.ui

import android.widget.ImageButton
import ru.barinov.jokesapplication.ui.uiModels.RecyclerViewItemModel

interface JokeItemClickListener {

    fun onFavoriteButtonPressed(item: RecyclerViewItemModel, action: (Boolean) -> Unit)

}
