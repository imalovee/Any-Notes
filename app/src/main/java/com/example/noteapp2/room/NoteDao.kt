package com.example.noteapp2.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.noteapp2.models.Notemodel
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert
 suspend fun saveNote(note: Notemodel)

 @Query("SELECT * FROM Notes  ")
  fun fetchNotes(): LiveData<List<Notemodel>>
}
