package com.example.noteapp2.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.noteapp2.models.Notemodel
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert
 suspend fun saveNote(note: Notemodel)

    @Query("SELECT * FROM notes order by id DESC  ")
    fun fetchNotes(): LiveData<List<Notemodel>>

    @Query("SELECT * FROM notes WHERE id = :noteId")
    fun fetchNote(noteId: String): LiveData<Notemodel>

    @Delete
    suspend fun deleteNote(note: Notemodel)

    @Update
    suspend fun updateNote(note: Notemodel)
}
