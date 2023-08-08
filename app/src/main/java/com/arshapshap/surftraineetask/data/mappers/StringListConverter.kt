package com.arshapshap.surftraineetask.data.mappers

import androidx.room.TypeConverter

class StringListConverter {

    @TypeConverter
    fun fromStringList(list: List<String>?): String? {
        return list?.joinToString("\n")
    }

    @TypeConverter
    fun toStringList(data: String?): List<String>? {
        return data?.split("\n")?.map { it.trim() }
    }
}