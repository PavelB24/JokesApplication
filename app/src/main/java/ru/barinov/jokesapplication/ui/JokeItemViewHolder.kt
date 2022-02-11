package ru.barinov.jokesapplication.ui

import androidx.recyclerview.widget.RecyclerView
import ru.barinov.jokesapplication.databinding.JokeItemLayoutBinding

class JokeItemViewHolder(binding: JokeItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {

    val jokeBodyTextView = binding.jokeBodyTextView
    val favoriteButton = binding.itemsFavoriteButton
}