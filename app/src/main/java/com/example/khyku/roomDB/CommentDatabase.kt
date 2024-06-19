package com.example.khyku.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Comment::class],
    version = 1,
    exportSchema = false
)
abstract class CommentDatabase: RoomDatabase(){
    abstract fun getDao():CommentDao
    companion object{
        private var database: CommentDatabase?=null
        fun getCommentDatabase(context: Context): CommentDatabase {
            return database ?:
            Room.databaseBuilder(
                context,
                CommentDatabase::class.java,
                "commentDB"
            ).build()
                .also{
                    database = it
                }
        }

    }
}