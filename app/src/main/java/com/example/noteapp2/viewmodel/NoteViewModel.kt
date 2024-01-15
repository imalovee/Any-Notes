package com.example.noteapp2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteapp2.models.Notemodel
import com.example.noteapp2.room.DatabaseConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteViewModel(private var applicationn: Application) : AndroidViewModel(Application()) {
    //calling the save function of the database
    private val db = DatabaseConfig.getInstance(applicationn)

    fun saveNote(title : String, content : String){
        //creating a note Instance
        val note =Notemodel(
            title = title,
            content = content
        )



        viewModelScope.launch{
            db.noteDao().saveNote(note)
        }

    }

    fun getAllNotes(): LiveData<List<Notemodel>> {
        return db.noteDao().fetchNotes()
    }

}