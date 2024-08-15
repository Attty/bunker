package com.example.bunker.presentation.distribute_attributes_screen.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.bunker.DistributeAttributesScreenObj

@Composable
fun DistributeAttributesScreen(modifier: Modifier = Modifier,
                               navController: NavController,
                               list: List<String>) {

    Column {
        list.forEach {
            Text(it)
        }
    }

}