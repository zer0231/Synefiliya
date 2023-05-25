package com.zero.synefiliya.utils.roomdb

import androidx.room.TypeConverter
import com.google.gson.Gson

class ArrayListConverter {
    @TypeConverter
    fun fromString(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()

    @TypeConverter
    fun listToJson(value: ArrayList<String>?) = Gson().toJson(value)

}