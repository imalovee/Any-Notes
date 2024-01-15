package com.example.noteapp2.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.noteapp2.ui.theme.Purple40
import com.example.noteapp2.viewmodel.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteScreen(navController: NavController){
    val noteViewModel: NoteViewModel = viewModel()
    var content by rememberSaveable { mutableStateOf("") }
    var title by rememberSaveable{mutableStateOf("")}

    Scaffold(
       topBar = {
           TopAppBar(title = { Text(text = "My Note")},
               colors = TopAppBarDefaults.smallTopAppBarColors(
                   containerColor = Purple40,
                   titleContentColor = Color.White,
                   actionIconContentColor = Color.White,
                   navigationIconContentColor = Color.White
               ),
               navigationIcon = {
                   IconButton(onClick = {  //saves the note
                       noteViewModel.saveNote(title, content)
                       //Take user back
                       navController.popBackStack() }) {
                       Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back Arrow" )
                   }
               }
           )

       },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(all=8.dp)
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
            ) {
                // Add your content here

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = title,
                    onValueChange = { value : String-> title =value},
                    label = {Text("Note Title")}
                )
                TextField(
                    value = content,
                    onValueChange = { newUserNote: String -> content = newUserNote },
                    label = { Text("Enter your note") },
                    modifier = Modifier
                        .fillMaxWidth()

                )
            }
        },

    )
}
