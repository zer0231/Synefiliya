package com.zero.synefiliya.utils.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.zero.synefiliya.fragments.detailFragment.models.MovieDetailAdditional
import com.zero.synefiliya.utils.Constants.Companion.MOVIE_COLUMN
import com.zero.synefiliya.utils.Constants.Companion.MOVIE_DATABASE


@Dao
interface MovieDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertMovie(movie: MovieDetailAdditional)

//    @Query("SELECT * FROM $MOVIE_DATABASE")
//    suspend fun getAllMovies(): List<MovieDetail>
    @Query("SELECT * FROM $MOVIE_COLUMN where id = :movie_id")
    suspend fun isBookmarked(movie_id:Int):List<MovieDetailAdditional>

    @Delete
    suspend fun deleteMovie(movie: MovieDetailAdditional)

}