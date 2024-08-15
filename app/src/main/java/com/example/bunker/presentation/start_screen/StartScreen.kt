package com.example.bunker.presentation.start_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bunker.NewgameScreenObj
import com.example.bunker.R

@Composable
fun StartScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Image(
        painterResource(id = R.drawable.start_screen), contentDescription = "background",
        modifier = Modifier.fillMaxSize(), contentScale = ContentScale.FillHeight
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = "Бункєр",
            fontSize = 48.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.W900
        )
        Column(modifier = Modifier.padding(top = 64.dp)) {
            Button(
                modifier = Modifier
                    .size(width = 222.dp, height = 66.dp)
                    .padding(bottom = 4.dp),
                onClick = { /*TODO*/ },
                enabled = false,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.Red,
                    disabledContentColor = Color.Gray.copy(alpha = 0.5f),
                    disabledContainerColor = Color.DarkGray.copy(alpha = 0.5f)
                ),
            ) {
                Text(
                    "Продовжити",
                    fontSize = 24.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.W400
                )
            }
            Button(
                modifier = Modifier
                    .size(width = 222.dp, height = 66.dp)
                    .padding(bottom = 4.dp),
                onClick = { navController.navigate(NewgameScreenObj) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.Red
                )
            ) {
                Text(
                    "Нова гра",
                    fontSize = 24.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.W400
                )
            }
            Button(
                modifier = Modifier
                    .size(width = 222.dp, height = 66.dp)
                    .padding(bottom = 4.dp),
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.Red
                )
            ) {
                Text(
                    "Правила",
                    fontSize = 24.sp,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.W400
                )
            }
        }

    }
}