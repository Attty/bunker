package com.example.bunker.presentation.distribute_attributes_screen.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bunker.DistributeAttributesScreenObj
import com.example.bunker.presentation.distribute_attributes_screen.viewmodel.AttributesScreenViewmodel

@Composable
fun DistributeAttributesScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    list: List<String>,
    viewmodel: AttributesScreenViewmodel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewmodel.DistributeAttributes(list)
    }
    val state = viewmodel.state.value

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        itemsIndexed(state.list) { index, item ->
            Card(
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(item.name)
                    Text(item.sex)
                    Text("${item.age}")
                    Text(item.job)
                    Text(item.humanTrait)
                    Text(item.health)
                    Text(item.hobby)
                    Text(item.phobia)
                    Text(item.baggage)
                    Text(item.additionalInfo)

                }
            }
        }
    }
}