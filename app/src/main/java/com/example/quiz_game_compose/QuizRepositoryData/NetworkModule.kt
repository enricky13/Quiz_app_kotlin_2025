package com.example.quiz_game_compose.QuizRepositoryData

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * This is the retrofit client, its a singleton so we don't
 * create more instances of this retrofit and accidentailly make extra calls than necessary. Feel free to
 * use this as a base from now on
 * This uses Dagger (Dependancy injection) so android knows tthat there should be one instance of this class and will
 * be used elsewhere in the app and should use this class as a base
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val BASE_URL = "https://opentdb.com/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideApiQuiz(retrofit: Retrofit): QuizApi{
        return retrofit.create(QuizApi::class.java)
    }
}