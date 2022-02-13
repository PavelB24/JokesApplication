package ru.barinov.jokesapplication.domain

data class RandomMultipleJokeResult(
    val type: String,
    val value: List<LoadedJokeEntity>
){
    companion object{
         const val SUCCESS_RESULT =  "success"
    }
}
