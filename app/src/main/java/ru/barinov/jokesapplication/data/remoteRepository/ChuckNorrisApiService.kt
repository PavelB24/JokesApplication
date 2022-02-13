package ru.barinov.jokesapplication.data.remoteRepository

import kotlinx.coroutines.flow.Flow
import retrofit2.*
import ru.barinov.jokesapplication.domain.RandomMultipleJokeResult

interface ChuckNorrisApiService {

    fun getRandomJokesByNumber(number: Int): Flow<RandomMultipleJokeResult>

}