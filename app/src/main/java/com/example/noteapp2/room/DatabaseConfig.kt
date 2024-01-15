package com.example.noteapp2.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteapp2.models.Notemodel

object DatabaseConfig {
    fun getInstance(context : Context): AppDatabase{
        val db = Room.databaseBuilder(
            context,
        AppDatabase::class.java,
        "note_db").build()
        return db
    }
}



@Database(entities = [Notemodel::class], version = 1,
    //exportSchema = false
)
abstract class AppDatabase : RoomDatabase(){

    abstract fun noteDao() : NoteDao
}
