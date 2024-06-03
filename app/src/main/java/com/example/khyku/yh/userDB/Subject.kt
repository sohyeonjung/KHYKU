package com.example.khyku.yh.userDB

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.Duration

data class Subject(
    val name:String,
    val cate:String,
//    var time:Int,
    var time:Duration,
    var isUserMain:Boolean
)

class SubjectListConverters {
    private val gson = Gson()

    @TypeConverter
    fun fromSubjectList(value: List<Subject>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toSubjectList(value: String?): List<Subject>? {
        val listType = object : TypeToken<List<Subject>>() {}.type
        return gson.fromJson(value, listType)
    }
}

