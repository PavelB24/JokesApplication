package ru.barinov.jokesapplication.ui

import android.view.*
import android.widget.ImageButton
import androidx.recyclerview.widget.*
import ru.barinov.jokesapplication.R
import ru.barinov.jokesapplication.databinding.JokeItemLayoutBinding

class JokesRecyclerViewAdapter: RecyclerView.Adapter<JokeItemViewHolder>() {

    private var itemList = emptyList<RecyclerViewItemModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeItemViewHolder {
        val viewHolderBinding: JokeItemLayoutBinding =
            JokeItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JokeItemViewHolder(viewHolderBinding)
    }

    override fun onBindViewHolder(holder: JokeItemViewHolder, position: Int) {

        val item = getItem(position)

        holder.jokeBodyTextView.text = item.joke
        setFavoriteButtonState(item.isFavorite, holder.favoriteButton)

        holder.favoriteButton.setOnClickListener {
            item.listener.onFavoriteButtonPressed(item){isFavorite->
                setFavoriteButtonState(isFavorite, holder.favoriteButton)
            }
//
        }

    }

    private fun setFavoriteButtonState(isFavorite: Boolean, favButton: ImageButton) {
        if (isFavorite){
            favButton.setImageResource(R.drawable.ic_favourites_selected_star)
        } else{
            favButton.setImageResource(R.drawable.ic_favourites_black_star)
        }

    }

    override fun getItemCount(): Int {

        return itemList.size
    }

    private fun getItem(position: Int): RecyclerViewItemModel{

        return itemList[position]
    }

    fun setItems(newData: List<RecyclerViewItemModel>){
        val result = DiffUtil.calculateDiff(DiffCallback(itemList, newData), true)
        itemList = newData
        result.dispatchUpdatesTo(this)
    }

}