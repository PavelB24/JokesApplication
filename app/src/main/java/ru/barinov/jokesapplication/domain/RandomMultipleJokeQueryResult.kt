package ru.barinov.jokesapplication.domain

data class RandomMultipleJokeQueryResult(
    val type: String,
    val value: List<JokeApiEntity>
)