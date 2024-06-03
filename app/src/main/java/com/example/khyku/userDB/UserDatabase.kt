// UserDatabase
package com.example.khyku.userDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [UserProfile::class],
    version = 1,
    exportSchema = false
)
abstract class UserProfileDatabase : RoomDatabase() {
    abstract fun getDao(): UserProfileDao

    companion object {
        @Volatile
        private var INSTANCE: UserProfileDatabase? = null
        fun getUserProfileDatabase(context: Context): UserProfileDatabase {
            // context.deleteDatabase("user_profile_database")
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserProfileDatabase::class.java,
                    "user_profile_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}