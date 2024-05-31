package com.example.khyku.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Post::class],
    version = 1,
    exportSchema = false
)
abstract class PostDatabase: RoomDatabase(){
    abstract fun getDao():PostDao
    companion object{ //외부에서, 객체 딱 1개만 만들어서 요청 -> 생성되어있는 db 객체 반환
        private var database: PostDatabase?=null
        fun getPostDatabase(context: Context): PostDatabase {
            return database ?:
            Room.databaseBuilder(
                context,
                PostDatabase::class.java,
                "postDB"
            ).build()
                .also{
                    database = it
                }
        }

    }
}