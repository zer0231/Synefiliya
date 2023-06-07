package com.zero.synefiliya.utils.di

import android.content.Context
import androidx.room.Room
import com.zero.synefiliya.fragments.detailFragment.models.MovieDetailAdditional
import com.zero.synefiliya.utils.Constants.Companion.MOVIE_DATABASE
import com.zero.synefiliya.utils.roomdb.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDatabaseClient {

    @Singleton
    @Provides
    fun provideMovieDatabase(@ApplicationContext context: Context): MovieDatabase {
        return Room.databaseBuilder(context, MovieDatabase::class.java, MOVIE_DATABASE).build()
    }

    @Provides
    @Singleton
    fun provideDao(db: MovieDatabase) = db.movieDao()

    @Provides
    fun provideEntity() = MovieDetailAdditional()


}