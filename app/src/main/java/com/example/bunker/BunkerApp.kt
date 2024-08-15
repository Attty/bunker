package com.example.bunker

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.bunker.presentation.distribute_attributes_screen.screen.DistributeAttributesScreen
import com.example.bunker.presentation.newgame_screen.screen.NewgameScreen
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
        composable<DistributeAttributesScreenObj> {
            val args = it.toRoute<DistributeAttributesScreenObj>()

            DistributeAttributesScreen(navController = navController, list = args.list)
        }
    }

}

@Serializable
object StartScreenObj

@Serializable
object NewgameScreenObj

@Serializable
data class DistributeAttributesScreenObj(
    val list: List<String>
)