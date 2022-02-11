package ru.barinov.jokesapplication.ui

import android.view.*
import androidx.recyclerview.widget.RecyclerView
import ru.barinov.jokesapplication.databinding.JokeItemLayoutBinding

class JokesRecyclerViewAdapter: RecyclerView.Adapter<JokeItemViewHolder>() {

    private var itemList = emptyList<JokeRecyclerItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeItemViewHolder {
        val viewHolderBinding: JokeItemLayoutBinding =
            JokeItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JokeItemViewHolder(viewHolderBinding)
    }

    override fun onBindViewHolder(holder: JokeItemViewHolder, position: Int) {

        val item = getItem(position)


    }

    override fun getItemCount(): Int {

        return itemList.size
    }

    private fun getItem(position: Int): JokeRecyclerItem{

        return itemList[position]
    }

}