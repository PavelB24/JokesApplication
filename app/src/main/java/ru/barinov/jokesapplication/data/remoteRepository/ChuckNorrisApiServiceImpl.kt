package ru.barinov.jokesapplication.data.remoteRepository

import kotlinx.coroutines.flow.*
import ru.barinov.jokesapplication.data.remoteDataBaseLoader.ChuckNorrisAPI
import ru.barinov.jokesapplication.domain.RandomMultipleJokeResult

class ChuckNorrisApiServiceImpl (
    private val api: ChuckNorrisAPI
): ChuckNorrisApiService {

    override  fun getRandomJokesByNumber(number: Int): Flow<RandomMultipleJokeResult>{
        return  api.loadRandomJokesByNumber(number)
    }



}