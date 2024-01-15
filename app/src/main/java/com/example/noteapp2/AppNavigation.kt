package com.example.noteapp2

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.noteapp2.screens.AddNoteScreen
import com.example.noteapp2.screens.NoteDetailsScreen
import com.example.noteapp2.screens.NoteListScreen


@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.NoteListRoute
    ){
        composable(Routes.NoteListRoute){
            NoteListScreen(navController)
        }

        composable(Routes.AddNoteRoute){
            AddNoteScreen(navController)
        }

        composable(Routes.NoteDetailsRoute){
            NoteDetailsScreen(navController)
        }
    }
}

object Routes{
    val NoteListRoute = "note-list"
    val AddNoteRoute = "add-note"
    val NoteDetailsRoute = "note-details"
}