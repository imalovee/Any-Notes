package com.example.noteapp2.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.noteapp2.component.NoteItem
import com.example.noteapp2.ui.theme.NoteApp2Theme
import com.example.noteapp2.Routes
import com.example.noteapp2.models.Notemodel
import com.example.noteapp2.viewmodel.NoteViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(navController: NavController) {
    val nViewModel : NoteViewModel = viewModel()
    val listOfNotes   by nViewModel.getAllNotes().observeAsState(emptyList())
    Scaffold(
        topBar = {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(4.dp) // Add a shadow to the card
            ) {
                TopAppBar(title = { Text(text = "MY Note App") },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor =MaterialTheme.colorScheme.primary,
                        titleContentColor = Color.White,
                        actionIconContentColor = Color.White
                    //MaterialTheme.colorScheme.tertiary,
                    ),
                    actions = {
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search for You"
                            )

                        }
                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "More Icons"
                            )

                        }
                    }
                )
            }},
        content = {paddingValues -> 
            LazyColumn(modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()) {
                items(listOfNotes){
                    NoteItem(note = it, navController = navController)
                }

            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Routes.AddNoteRoute) }) {
               Icon(imageVector = Icons.Default.Add, contentDescription ="null" )
                
            }
        },
    )}

@Preview(showBackground = true)
@Composable
fun NoteListScreenPreview() {
    NoteApp2Theme {
        //NoteListScreen()
    }
}