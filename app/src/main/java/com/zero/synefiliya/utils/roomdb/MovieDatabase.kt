package com.zero.synefiliya.utils.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zero.synefiliya.fragments.detailFragment.models.MovieDetailAdditional

@Database(entities = [MovieDetailAdditional::class], version = 2, exportSchema = false)

abstract class MovieDatabase : RoomDatabase() {


    abstract fun movieDao(): MovieDao
}