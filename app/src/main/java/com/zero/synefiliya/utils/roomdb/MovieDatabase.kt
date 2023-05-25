package com.zero.synefiliya.utils.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zero.synefiliya.fragments.dashboardFragment.models.MovieDetail

@Database(entities = [MovieDetail::class], version = 1, exportSchema = true)
@TypeConverters(ArrayListConverter::class, TorrentListConverter::class)

abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}