package com.example.bunker.presentation.newgame_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import com.example.bunker.ui.theme.Gray

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun NewgameScreen(modifier: Modifier = Modifier) {
    var list by remember {
        mutableStateOf(mutableListOf("Влад", "Соня", "add"))
    }

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    fun addItemBeforeLast(newItem: String) {
        val newList = if (list.size > 1) {
            list.subList(0, list.size - 1) + newItem + list.last()
        } else {
            list + newItem
        }
        list = newList.toMutableList()
    }

    fun updateName(index: Int, newName: String) {
        if (index in list.indices) {
            list = list.toMutableList().apply {
                this[index] = newName
            }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Гравці",
                        fontSize = 24.sp,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.W600
                    )
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier.padding(8.dp, end = 16.dp),
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "back"
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.Red,
                    navigationIconContentColor = Color.Red
                )
            )
        },
        bottomBar = {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Red,
                    containerColor = Gray
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 16.dp
                )
            ) {
                Text(
                    "Далі",
                    fontSize = 24.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.W800
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.Red),
        ) {
            itemsIndexed(list) { index, item ->
                if (item == "add") {
                    Button(
                        onClick = {
                            addItemBeforeLast("Новий гравець")
                            focusManager.clearFocus()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(64.dp),
                        shape = RectangleShape,
                        colors = ButtonDefaults.buttonColors(
                            contentColor = Color.Red,
                            containerColor = Color.Black
                        ),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = (-16).dp
                        )
                    ) {
                        Text(
                            "+",
                            fontSize = 24.sp,
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.W800
                        )
                    }
                } else {
                    var editableName by remember {
                        mutableStateOf(item)
                    }
                    BasicTextField(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth()
                            .focusRequester(focusRequester),
                        value = editableName, onValueChange = {
                            editableName = it
                            updateName(index, it)
                        },
                        readOnly = false,
                        textStyle = TextStyle.Default,
                        singleLine = true,
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus()
                            }
                        )
                    )

                }
            }
        }
    }
}