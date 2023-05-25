package com.zero.synefiliya.utils.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.zero.synefiliya.fragments.dashboardFragment.models.MovieDetail
import com.zero.synefiliya.utils.Constants.Companion.MOVIE_DATABASE


@Dao
interface MovieDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertMovie(movie: MovieDetail)

    @Query("SELECT * FROM $MOVIE_DATABASE")
    suspend fun getAllMovies(): List<MovieDetail>

    @Delete
    suspend fun deleteMovie(movie: MovieDetail)

}