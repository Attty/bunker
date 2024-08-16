package com.example.bunker.presentation.newgame_screen.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bunker.DistributeAttributesScreenObj
import com.example.bunker.R
import com.example.bunker.StartScreenObj
import com.example.bunker.presentation.newgame_screen.viewmodel.NewgameScreenViewmodel
import com.example.bunker.ui.theme.CustomRed
import com.example.bunker.ui.theme.Gray
import com.example.bunker.ui.theme.NewgameScreenBG
import com.example.bunker.ui.theme.TextFieldBG
import dagger.hilt.android.lifecycle.HiltViewModel

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewgameScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewmodel: NewgameScreenViewmodel = hiltViewModel()
) {
    val state = viewmodel.state.value
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current


    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(148.dp),
                containerColor = NewgameScreenBG
            ) {
                Button(
                    onClick = {
                        viewmodel.addItem("")
                        focusManager.clearFocus()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, bottom = 48.dp, end = 16.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.Red,
                        containerColor = Color.Black
                    ),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = (-16).dp
                    )
                ) {
                    Image(painterResource(id = R.drawable.plus), contentDescription = "",
                        modifier = Modifier.size(38.dp))
                }
            }
        },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Image(painterResource(id = R.drawable.bunker), contentDescription = "bunker")
                },
                navigationIcon = {
                    Image(
                        painterResource(id = R.drawable.back_button),
                        contentDescription = "back",
                        modifier = Modifier
                            .clickable {
                                navController.navigate(StartScreenObj)
                            }
                            .padding(start = 12.dp)
                            .size(32.dp)
                    )
                },
                actions = {
                    IconButton(onClick = {
                        if (!state.list.contains("")) {
                            navController.navigate(DistributeAttributesScreenObj(state.list))
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = "next",
                            modifier = Modifier
                                .padding(end = 12.dp)
                                .size(32.dp),
                            tint = CustomRed
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Black,
                )
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(NewgameScreenBG),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                itemsIndexed(state.list) { index, item ->
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, top = 8.dp, end = 20.dp)
                            .focusRequester(focusRequester),
                        textStyle = TextStyle(fontWeight = FontWeight.W700),
                        singleLine = true,
                        value = item,
                        onValueChange = {
                            viewmodel.updateName(index, it)
                        },
                        placeholder = { Text(text = "Гравець ${index + 1}") },
                        shape = RoundedCornerShape(8.dp),
                        keyboardOptions = KeyboardOptions.Default.copy(
                            imeAction = ImeAction.Done,
                            capitalization = KeyboardCapitalization.Sentences
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus()
                                keyboardController?.hide()
                            }
                        ),
                        trailingIcon = {
                            if (state.list.size > 2) {
                                IconButton(onClick = {
                                    viewmodel.removeItem(index)
                                    focusManager.clearFocus()
                                    keyboardController?.hide()
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = ""
                                    )
                                }
                            }
                        },
                        colors = TextFieldDefaults.colors(
                            focusedTrailingIconColor = CustomRed,
                            unfocusedTrailingIconColor = NewgameScreenBG,
                            focusedTextColor = CustomRed,
                            unfocusedTextColor = CustomRed,
                            unfocusedPlaceholderColor = NewgameScreenBG,
                            focusedPlaceholderColor = NewgameScreenBG,
                            focusedContainerColor = Color.Black,
                            unfocusedContainerColor = Color.Black,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            cursorColor = CustomRed
                        )
                        )
                }
            }
        }

    }
}