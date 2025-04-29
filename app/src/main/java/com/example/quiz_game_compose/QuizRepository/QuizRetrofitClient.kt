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
    private const val BASE_URL = "https://opentdb.com/api.php"

    val instance: QuizApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(QuizApi::class.java)
    }

    fun fetchTriviaQuestions(amount: Int, category: Int, difficulty: String, type: String){
        val call = instance.getQuestions(amount, category, difficulty, type)
        call.enqueue(object : Callback<QuizResponse>{
            override fun onResponse(call: Call<QuizResponse>, response: Response<QuizResponse>) {
                /**
                 * Network call is successful do whatever view things or pass on you need to do here
                 */
                if (response.isSuccessful){
                    val triviaData = response.body()
                    triviaData?.results?.forEach{
                        println("Question: ${it.question} Correct Answer: ${it.correctAnswer} Incorrect Answers: ${it.incorrectAnswers}")
                    }
                }
            }

            override fun onFailure(call: Call<QuizResponse>, response: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}