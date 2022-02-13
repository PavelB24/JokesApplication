package ru.barinov.jokesapplication.ui.jokesFragment

import androidx.lifecycle.*
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import ru.barinov.jokesapplication.data.localRepository.LocalRepository
import ru.barinov.jokesapplication.data.remoteRepository.ChuckNorrisApiService
import ru.barinov.jokesapplication.domain.*
import ru.barinov.jokesapplication.ui.*
import ru.barinov.jokesapplication.ui.uiModels.*
import java.lang.Exception

class JokesViewModel(
    private val localRepository: LocalRepository, private val remoteRepository: ChuckNorrisApiService
) : ViewModel() {

    private val quantity: MutableStateFlow<Int> = MutableStateFlow(0)

    private val favoritesSelected: MutableStateFlow<Boolean> = MutableStateFlow(false)


    val mainJokesFlow: StateFlow<List<RecyclerViewItemModel>?> = combine(
        setupRandomJokesFlow(),
        localRepository.getFavoriteJokes(),
        favoritesSelected
    ) { randomJokes, favoriteJokes, isFavoritesSelected ->
        return@combine if (isFavoritesSelected) {
            favoriteJokes.map { jokeEntity ->
                jokeEntity.toRecyclerViewItemModel(sendListener())
            }
        } else randomJokes
    }
        .flowOn(Dispatchers.IO)
        .stateIn(viewModelScope, SharingStarted.Lazily, null)




    private fun setupRandomJokesFlow(): Flow<List<RecyclerViewItemModel>?> {
        return quantity.map { quantityFlowValue ->
            val result: RandomMultipleJokeResult? = try {
                remoteRepository.getRandomJokesByNumber(quantityFlowValue).single()
            } catch (e: Exception) {
                null
            }
            return@map if (result?.type == RandomMultipleJokeResult.SUCCESS_RESULT) {
                result.value.map { loadedJoke ->
                    loadedJoke.toRecyclerViewItemModel(sendListener(), System.currentTimeMillis())
                }
            } else {
                emptyList()
            }
        }
    }

    fun onFavoriteClicked(isChecked: Boolean) {
        favoritesSelected.value = isChecked
    }

    fun loadJokesFromApi(number: Int) {
        quantity.value = number
    }

    private fun sendListener(): JokeItemClickListener {
        return object : JokeItemClickListener {
            override fun onFavoriteButtonPressed(item: RecyclerViewItemModel, action: (Boolean) -> Unit) {
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        val favSavedJokeEntity = item.toFavoriteSavedJoke()
                        if (!item.isFavorite) {
                            localRepository.addJoke(favSavedJokeEntity)
                        } else {
                            localRepository.deleteJoke(favSavedJokeEntity)
                        }
                        item.isFavorite = !item.isFavorite
                        withContext(Dispatchers.Main) {
                               action.invoke(item.isFavorite)
                            }
                        }
                    }
                }
            }
        }
    }