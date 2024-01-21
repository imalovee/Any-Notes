package com.example.noteapp2.component

import android.os.Build
import android.provider.ContactsContract
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.noteapp2.Routes
import com.example.noteapp2.models.Notemodel
import com.example.noteapp2.viewmodel.NoteViewModel
import kotlinx.coroutines.delay
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteItem(note: Notemodel, navController: NavController){
    val noteViewModel: NoteViewModel = viewModel()
    val dismissState = rememberSwipeToDismissBoxState()
    val colorToBeShown by animateColorAsState(
        targetValue = if (dismissState.targetValue == SwipeToDismissBoxValue.EndToStart){
            Color.Red
        }else{
            Color.White
        },
        label = ""
    )
    var undoDelete by remember{ mutableStateOf(false) }

    if (dismissState.targetValue == SwipeToDismissBoxValue.EndToStart){
        LaunchedEffect(key1 = "delete"){
            delay(5000)
            if(!undoDelete){
                noteViewModel.deleteNote(note)
            }
        }
    }
    if(undoDelete){
        LaunchedEffect(key1 = "undo"){
            dismissState.reset()
            undoDelete = false
        }
    }

//    val timestamp = Instant.ofEpochMilli(note.dateTime)
//    val dateTime = LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault())
//
//    val formattedTime = dateTime.format(DateTimeFormatter.ofPattern("EEEE MMM. d, yyyy hh:mm a"))

    SwipeToDismissBox(state = dismissState , backgroundContent = {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(colorToBeShown)
            .padding(horizontal = 8.dp)
        ){
            Button(onClick = { undoDelete = true  },
                    modifier = Modifier.align(Alignment.CenterEnd)) {
                Text(text = "UNDO")

            }
        }
    } ) {  

        Card ( modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
            colors = CardColors(
                containerColor = Color.Blue,
                disabledContainerColor = Color.Black,
                contentColor = Color.White,
                disabledContentColor = Color.Black)
        )
        {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { navController.navigate(Routes.NoteDetails(note.id.toString())) })
            {
                Text(text = note.title,
                    fontWeight = FontWeight.Bold,)
                Text(text=note.content, maxLines = 4)

//                Text(
//                    text = formattedTime,
//                    color = Color.Black,
//                    fontSize = 12.sp,
//                    modifier = Modifier
//                        .align(Alignment.End))

            }

        }
    }

}

