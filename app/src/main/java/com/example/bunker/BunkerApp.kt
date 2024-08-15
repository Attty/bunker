package com.example.bunker

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bunker.presentation.newgame_screen.NewgameScreen
import com.example.bunker.presentation.start_screen.StartScreen
import kotlinx.serialization.Serializable

@Composable
fun BunkerApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = StartScreenObj) {
        composable<StartScreenObj> {
            StartScreen(navController = navController)
        }
        composable<NewgameScreenObj> {
            NewgameScreen(navController = navController)
        }
    }

}

@Serializable
object StartScreenObj

@Serializable
object NewgameScreenObj