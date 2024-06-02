// TypeConverters
package com.example.khyku.yh.userDB

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.time.Duration
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun mapToString(value: MutableMap<String, Duration>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun stringToMap(value: String): MutableMap<String, Duration> {
        val mapType = object : TypeToken<MutableMap<String, Duration>>() {}.type
        return gson.fromJson(value, mapType)
    }

    @TypeConverter
    fun fromDuration(value: Duration?): Long? {
        return value?.toMillis()
    }

    @TypeConverter
    fun toDuration(value: Long?): Duration? {
        return if (value != null) Duration.ofMillis(value) else null
    }

    private val formatter = DateTimeFormatter.ISO_LOCAL_TIME

    @TypeConverter
    fun fromLocalTime(time: LocalTime?): String? {
        return time?.format(formatter)
    }

    @TypeConverter
    fun toLocalTime(timeString: String?): LocalTime? {
        return try {
            if (timeString != null) LocalTime.parse(timeString, formatter) else null
        } catch (e: DateTimeParseException) {
            null
        }
    }
}