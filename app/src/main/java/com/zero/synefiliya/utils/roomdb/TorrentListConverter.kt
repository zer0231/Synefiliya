package com.zero.synefiliya.utils.roomdb

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.zero.synefiliya.fragments.dashboardFragment.models.TorrentDetail

class TorrentListConverter {
    @TypeConverter
    fun fromTorrentList(value: String) = Gson().fromJson(value, Array<TorrentDetail>::class.java).toList()

    @TypeConverter
    fun torrentListToJson(value: ArrayList<TorrentDetail>?) = Gson().toJson(value)
}