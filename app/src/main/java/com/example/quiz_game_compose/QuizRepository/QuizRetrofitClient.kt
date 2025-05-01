package com.example.quiz_game_compose.QuizRepository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This is the retrofit client, its a singleton so we don't
 * create more instances of this retrofit and accidentailly make extra calls than necessary. Feel free to
 * use this as a base from now on
 */
object QuizRetrofitClient {
    private const val BASE_URL = "https://opentdb.com/"

    val instance: QuizApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuizApi::class.java)
    }
}