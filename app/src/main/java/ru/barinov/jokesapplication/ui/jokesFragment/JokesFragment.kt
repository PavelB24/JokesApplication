package ru.barinov.jokesapplication.ui.jokesFragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.barinov.jokesapplication.R
import ru.barinov.jokesapplication.databinding.JokesFragmentLayoutBinding
import ru.barinov.jokesapplication.ui.JokesRecyclerViewAdapter
import ru.barinov.jokesapplication.ui.activity.MainActivity

private const val FAVORITES_BUTTON_STATE_KEY = "FAVORITES_BUTTON_STATE_KEY"


class JokesFragment : Fragment() {

    private var _binding: JokesFragmentLayoutBinding? = null
    private val binding
        get() = _binding!!

    private var favoritesMenuButtonState: Boolean = false

    private lateinit var recyclerView: RecyclerView
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private val adapter = JokesRecyclerViewAdapter()

    private val viewModel by viewModel<JokesViewModel>()



    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            onViewStateRestored(savedInstanceState)
        }
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = JokesFragmentLayoutBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        if (savedInstanceState!= null){

            val isChecked = savedInstanceState.getBoolean(FAVORITES_BUTTON_STATE_KEY)
            favoritesMenuButtonState = isChecked

        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initViews()

        lifecycleScope.launchWhenStarted {
            withContext(Dispatchers.Main) {
                viewModel.mainJokesFlow.onEach { jokesList ->
                    if (jokesList != null) {
                        adapter.setItems(jokesList)
                    }
                }.collect()
            }
        }


        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        if (!menu.hasVisibleItems()) {
            inflater.inflate(R.menu.toolbar_menu, menu)

            val favButton = menu.findItem(R.id.favorites_menu_button)
            favButton.isChecked= favoritesMenuButtonState
            setToolbarFavoriteButtonState(favoritesMenuButtonState, favButton)

            super.onCreateOptionsMenu(menu, inflater)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.favorites_menu_button -> {
                    item.isChecked = !item.isChecked
                    val isChecked = item.isChecked
                    viewModel.onFavoriteClicked(isChecked)
                    setToolbarFavoriteButtonState(isChecked, item)
                }
            }
        return super.onOptionsItemSelected(item)
    }

    private fun setToolbarFavoriteButtonState(isChecked: Boolean, item: MenuItem) {

        if (isChecked) {
            item.setIcon(R.drawable.ic_favourites_selected_star)
        } else {
            item.setIcon(R.drawable.ic_favourites_black_star)
        }
    }

    private fun initViews() {

        toolbar= binding.toolbar
        (requireActivity() as MainActivity).setSupportActionBar(toolbar)
        recyclerView = binding.recyclerView
        recyclerView.adapter = adapter

        binding.resetButton.setOnClickListener {

            var text = binding.jokesToLoadValueEditText.text.toString()
            if (text.isEmpty()) {
                text = "0"
            }
            val toolbarFavoriteButton= toolbar.menu.findItem(R.id.favorites_menu_button)
            toolbarFavoriteButton.isChecked = false

            viewModel.onFavoriteClicked( toolbarFavoriteButton.isChecked)
            setToolbarFavoriteButtonState( toolbarFavoriteButton.isChecked, toolbarFavoriteButton)

            viewModel.loadJokesFromApi(
                text.toInt()
            )

        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (this.isVisible) {
            outState.putBoolean(
                FAVORITES_BUTTON_STATE_KEY, toolbar.menu.findItem(R.id.favorites_menu_button).isChecked
            )
        }
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object{
         const val BASE_URL = "https://api.icndb.com/"
    }
}