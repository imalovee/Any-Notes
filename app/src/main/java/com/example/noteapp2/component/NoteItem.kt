package com.example.noteapp2.component

import android.provider.ContactsContract
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.noteapp2.Routes
import com.example.noteapp2.models.Notemodel

@Composable
fun NoteItem(note: Notemodel, navController: NavController){
    Card ( modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp))
    {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { navController.navigate(Routes.NoteDetailsRoute) })
        {
            Text(text = note.title,
                fontWeight = FontWeight.Bold)
            Text(text=note.content)
            Text(text = "10:58am" , color = Color.Red, modifier=Modifier.align(Alignment.End))

        }

    }
}
