package com.santosh.popularmovies.dagger

import com.santosh.popularmovies.api.MoviesApi
import com.santosh.popularmovies.api.RetrofitInstance
import com.santosh.popularmovies.repository.MovieRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideMoviesApi(): MoviesApi {
        return RetrofitInstance.api
    }

    @Provides
    @Singleton
    fun providesMoviesRepo(moviesApi: MoviesApi): MovieRepo {
        return MovieRepo(moviesApi)
    }

}