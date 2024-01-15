package com.example.noteapp2.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "Notes")
data class Notemodel(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val title : String,
    val content : String,


)
