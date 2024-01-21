package com.example.noteapp2.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.noteapp2.Routes
import com.example.noteapp2.models.Notemodel
import com.example.noteapp2.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailsScreen(navController: NavController, noteId: String) {
    val noteViewModel: NoteViewModel = viewModel()
    val note by noteViewModel.getNote(noteId).observeAsState()
    var title by rememberSaveable{ mutableStateOf( "") }
    var content by rememberSaveable{ mutableStateOf("") }
    var isEditMode by rememberSaveable{ mutableStateOf(false) }

    fun activateEditMode(){
        isEditMode = true
        title = note?.title ?: ""
        content = note?.content ?: ""
    }
    Scaffold (
        topBar = {
            TopAppBar(title = { Text(text = "Edit-Note") },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White,
                            navigationIconContentColor = Color.White
                    //MaterialTheme.colorScheme.tertiary,
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        //Take user back
                        navController.popBackStack() }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back Arrow" )
                    }
                },
                actions = {
                    if(isEditMode){
                        //show save icon
                        IconButton(onClick = {
                            val updatedNote: Notemodel = note!!.copy(
                                title=title,
                                content=content
                            )
                            noteViewModel.updateNote(updatedNote)
                            isEditMode = false
                        }){
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = "Save note"
                            )
                        }
                    }else{
                        //show edit icon
                        IconButton(onClick = {
                            activateEditMode()
                        }){
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Edit note"
                            )
                        }}
                    IconButton(onClick = { noteViewModel.deleteNote(note!!)

                         }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "delete"
                        )

                    }

                }
            )
        }
    ){ paddingValues ->
        Column(modifier = Modifier.padding( paddingValues),
           ) {
            if(isEditMode){
                NotesTextField(
                    value = title ?: "",
                    onValueChange = {value-> title=value},
                    modifier = Modifier.fillMaxWidth(1f),
                    label = "Title"
                )
                NotesTextField(
                    value = content ?: "",
                    onValueChange = {value-> content=value},
                    modifier = Modifier.fillMaxWidth(1f),
                    label = "Content"
                )
            }else{
                Text(
                    note?.title ?: "No Title",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth() .padding(10.dp) .clickable {
                        activateEditMode()
                    }
                )
                Text(
                    note?.content ?: "No Content",
                    modifier = Modifier.fillMaxWidth() .padding(10.dp).clickable {
                        activateEditMode()
                    }
                )
            }
        }
    }
        }

