package ru.barinov.jokesapplication.data.remoteDataBaseLoader


import kotlinx.coroutines.flow.Flow
import retrofit2.http.*
import ru.barinov.jokesapplication.domain.RandomMultipleJokeResult

interface ChuckNorrisAPI {

    @GET("/jokes/random/{count}")
    fun loadRandomJokesByNumber(
        @Path("count")
        quantity: Int
    )
    : Flow<RandomMultipleJokeResult>


}