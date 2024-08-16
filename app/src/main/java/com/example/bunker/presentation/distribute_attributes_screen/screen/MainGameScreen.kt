package com.example.bunker.presentation.distribute_attributes_screen.screen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bunker.StartScreenObj
import com.example.bunker.domain.Enabled
import com.example.bunker.domain.Model
import com.example.bunker.presentation.distribute_attributes_screen.viewmodel.AttributesScreenViewmodel
import com.example.bunker.ui.theme.CustomRed
import com.example.bunker.ui.theme.NewgameScreenBG
import com.example.bunker.ui.theme.TextFieldBG

@Composable
fun MainGameScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    list: List<String>,
    viewmodel: AttributesScreenViewmodel = hiltViewModel()
) {
    val state = viewmodel.state.value

    LaunchedEffect(key1 = true) {
        viewmodel.CalculationMaxLoops(list.size)
        viewmodel.DistributeAttributes(list)
        viewmodel.ShowFirstPerson()
        viewmodel.CalculationMaxAttributesToChoose()
    }
    val context = LocalContext.current
    var choosenPerson by remember {
        mutableStateOf(
            Model(
                name = "",
                virtualName = "",
                age = 0,
                sex = "",
                job = "",
                body = "",
                humanTrait = "",
                health = "",
                hobby = "",
                phobia = "",
                baggage = "",
                additionalInfo = "",
                isDead = false
            )
        )
    }
    if (state.kickedPlayer == Model(
            name = "",
            virtualName = "",
            age = 0,
            sex = "",
            job = "",
            body = "",
            humanTrait = "",
            health = "",
            hobby = "",
            phobia = "",
            baggage = "",
            additionalInfo = "",
            isDead = false
        ) && state.isLoopEnd
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(NewgameScreenBG),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Коло закінчилось!", color = CustomRed,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W900,
                fontSize = 32.sp
            )
            Text(
                text = "Виберіть кого хочете кікнути", color = CustomRed,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W800,
                fontSize = 26.sp
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxHeight(0.6f)
                    .fillMaxHeight()
            ) {
                itemsIndexed(viewmodel.filterAlivePlayers(state.list)) { index, item ->
                    Card(
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .clickable { choosenPerson = item }
                            .padding(16.dp),
                        border = if (choosenPerson == item) {
                            BorderStroke(2.dp, Color.Red)
                        } else {
                            BorderStroke(2.dp, Color.Black)
                        },
                        colors = CardDefaults.cardColors(
                            containerColor = TextFieldBG
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(item.name, color = CustomRed)
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Text(item.virtualName, color = CustomRed)
                                Text("Пол: ${item.sex}", color = CustomRed)
                                Text("Професія: ${item.job}", color = CustomRed)
                                Text(
                                    text = "Вік : ${
                                        if (item.visibility.isShowedAge) {
                                            item.age.toString()
                                        } else "..."
                                    }", color = CustomRed
                                )
                                Text(
                                    text = "Статура : ${
                                        if (item.visibility.isShowedBody) {
                                            item.body
                                        } else "..."
                                    }", color = CustomRed
                                )

                                Text(
                                    text = "Людська риса : ${
                                        if (item.visibility.isShowedHumanTrait) {
                                            item.humanTrait
                                        } else "..."
                                    }", color = CustomRed
                                )

                                Text(
                                    text = "Здоров'я : ${
                                        if (item.visibility.isShowedHealth) {
                                            item.health
                                        } else "..."
                                    }", color = CustomRed
                                )

                                Text(
                                    text = "Хоббі : ${
                                        if (item.visibility.isShowedHobby) {
                                            item.hobby
                                        } else "..."
                                    }", color = CustomRed
                                )

                                Text(
                                    text = "Фобія : ${
                                        if (item.visibility.isShowedPhobia) {
                                            item.phobia
                                        } else "..."
                                    }", color = CustomRed
                                )

                                Text(
                                    text = "Багаж : ${
                                        if (item.visibility.isShowedBaggage) {
                                            item.baggage
                                        } else "..."
                                    }", color = CustomRed
                                )
                                Text(
                                    text = "Дод. інфа : ${
                                        if (item.visibility.isShowedAdditionalInfo) {
                                            item.additionalInfo
                                        } else "..."
                                    }", color = CustomRed
                                )
                            }
                        }
                    }
                }
            }
            Button(
                onClick = {
                    if (choosenPerson == Model(
                            name = "",
                            virtualName = "",
                            age = 0,
                            sex = "",
                            job = "",
                            body = "",
                            humanTrait = "",
                            health = "",
                            hobby = "",
                            phobia = "",
                            baggage = "",
                            additionalInfo = "",
                            isDead = false
                        )
                    ) {
                        Toast.makeText(context, "Виберіть особу", Toast.LENGTH_SHORT).show()
                    } else {
                        viewmodel.NewLoop(choosenPerson)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                border = BorderStroke(2.dp, Color.Black),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = TextFieldBG
                )
            ) {
                Text(text = "Кікнути", color = CustomRed)
            }
        }

    } else if (!state.isLoopEnd && state.isGameFinished) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(NewgameScreenBG),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "Гру закінчено!",
                color = CustomRed,
                fontSize = 32.sp,
                fontWeight = FontWeight.W900
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f)
                    .padding(16.dp),
            ) {
                Text(
                    text = "Список людей які пройшли в бункер:",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W700,
                    textAlign = TextAlign.Center
                )
                LazyRow(
                    modifier = Modifier.fillMaxSize()
                ) {
                    itemsIndexed(viewmodel.filterAlivePlayers(state.list)) { index, item ->
                        Card(
                            modifier = Modifier
                                .fillParentMaxWidth()
                                .padding(16.dp),
                            border = BorderStroke(1.dp, Color.Black),
                            colors = CardDefaults.cardColors(
                                containerColor = TextFieldBG
                            )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Text(text = item.name, color = CustomRed)
                                Column(
                                    Modifier
                                        .fillMaxWidth()
                                        .fillMaxSize()
                                        .padding(4.dp),
                                    verticalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Text("Ім'я: ${item.virtualName}", color = CustomRed)
                                    Text("Пол: ${item.sex}", color = CustomRed)
                                    Text("Професія: ${item.job}", color = CustomRed)
                                    Text("Вік: ${item.age}", color = CustomRed)
                                    Text("Статура: ${item.body}", color = CustomRed)
                                    Text("Людська риса: ${item.humanTrait}", color = CustomRed)
                                    Text("Здоров'я: ${item.health}", color = CustomRed)
                                    Text("Хоббі: ${item.hobby}", color = CustomRed)
                                    Text("Фобія: ${item.phobia}", color = CustomRed)
                                    Text("Багаж: ${item.baggage}", color = CustomRed)
                                    Text("Дод. інфа: ${item.additionalInfo}", color = CustomRed)
                                }

                            }
                        }
                    }
                }
            }
            Button(
                onClick = { navController.navigate(StartScreenObj) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                border = BorderStroke(1.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(
                    containerColor = TextFieldBG,
                )
            ) {
                Text(text = "Меню", color = CustomRed)
            }
        }
    } else if (state.kickedPlayer != Model(
            name = "",
            virtualName = "",
            age = 0,
            sex = "",
            job = "",
            body = "",
            humanTrait = "",
            health = "",
            hobby = "",
            phobia = "",
            baggage = "",
            additionalInfo = "",
            isDead = false
        ) && state.isLoopEnd
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(NewgameScreenBG)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                "Цей гравець покидає гру: ", color = CustomRed,
                fontSize = 32.sp,
                fontWeight = FontWeight.W900,
                textAlign = TextAlign.Center
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.6f),
                border = BorderStroke(2.dp, Color.Black),
                colors = CardDefaults.cardColors(
                    containerColor = TextFieldBG
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(state.kickedPlayer.name, color = CustomRed)
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Text(text = state.kickedPlayer.virtualName, color = CustomRed)

                        Text(text = "Стать: ${state.kickedPlayer.sex}", color = CustomRed)

                        Text(text = "Вік: ${state.kickedPlayer.age}", color = CustomRed)

                        Text(text = "Професія: ${state.kickedPlayer.job}", color = CustomRed)

                        Text(text = "Тип статури: ${state.kickedPlayer.body}", color = CustomRed)

                        Text(
                            text = "Риса характеру: ${state.kickedPlayer.humanTrait}",
                            color = CustomRed
                        )

                        Text(
                            text = "Стан здоров'я: ${state.kickedPlayer.health}",
                            color = CustomRed
                        )

                        Text(text = "Хоббі: ${state.kickedPlayer.hobby}", color = CustomRed)

                        Text(text = "Фобія: ${state.kickedPlayer.phobia}", color = CustomRed)

                        Text(text = "Багаж: ${state.kickedPlayer.baggage}", color = CustomRed)

                        Text(
                            text = "Додаткова інформація: ${state.kickedPlayer.additionalInfo}",
                            color = CustomRed
                        )
                    }
                }
            }
            Button(
                onClick = { viewmodel.LoopEnd() },
                modifier = Modifier.fillMaxWidth(),
                border = BorderStroke(2.dp, Color.Black),
                colors = ButtonDefaults.buttonColors(
                    containerColor = TextFieldBG
                )
            ) {
                Text("Наступне коло", color = CustomRed)
            }
        }
    } else {
        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        var selectedCount by remember { mutableStateOf(0) }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(NewgameScreenBG)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = state.currentPlayer.name,
                color = CustomRed,
                fontSize = 24.sp,
                fontWeight = FontWeight.W700
            )
            Column(
                Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                AttributeCard(
                    label = state.currentPlayer.virtualName,
                    attribute = "Ім'я",
                    show = state.show,
                    isEnabled = state.currentPlayer.enabled.isEnabledVirtualName,
                    selectedCount = selectedCount,
                    maxSelection = state.maxAttributesToChoose,
                    isVisible = state.currentPlayer.visibility.isShowedVirtualName
                ) { isChecked ->
                }
                AttributeCard(
                    label = state.currentPlayer.sex,
                    attribute = "Пол",
                    show = state.show,
                    isEnabled = state.currentPlayer.enabled.isEnabledSex,
                    selectedCount = selectedCount,
                    maxSelection = state.maxAttributesToChoose,
                    isVisible = state.currentPlayer.visibility.isShowedSex
                ) { isChecked ->
                }
                AttributeCard(
                    label = state.currentPlayer.job,
                    attribute = "Професія",
                    show = state.show,
                    isEnabled = state.currentPlayer.enabled.isEnabledJob,
                    selectedCount = selectedCount,
                    maxSelection = state.maxAttributesToChoose,
                    isVisible = state.currentPlayer.visibility.isShowedJob
                ) { isChecked ->
                }
                AttributeCard(
                    label = state.currentPlayer.age.toString(),
                    attribute = "Вік",
                    show = state.show,
                    isEnabled = state.currentPlayer.enabled.isEnabledAge,
                    selectedCount = selectedCount,
                    maxSelection = state.maxAttributesToChoose,
                    isVisible = state.currentPlayer.visibility.isShowedAge
                ) { isChecked ->
                    if (isChecked) selectedCount++ else selectedCount--
                    viewmodel.ShowAttributes(state.currentPlayer.visibility.copy(isShowedAge = isChecked))
                }
                AttributeCard(
                    label = state.currentPlayer.body,
                    attribute = "Статура",
                    show = state.show,
                    isEnabled = state.currentPlayer.enabled.isEnabledBody,
                    selectedCount = selectedCount,
                    maxSelection = state.maxAttributesToChoose,
                    isVisible = state.currentPlayer.visibility.isShowedBody
                ) { isChecked ->
                    if (isChecked) selectedCount++ else selectedCount--
                    viewmodel.ShowAttributes(state.currentPlayer.visibility.copy(isShowedBody = isChecked))
                }

                AttributeCard(
                    label = state.currentPlayer.humanTrait,
                    attribute = "Людська риса",
                    show = state.show,
                    isEnabled = state.currentPlayer.enabled.isEnabledHumanTrait,
                    selectedCount = selectedCount,
                    maxSelection = state.maxAttributesToChoose,
                    isVisible = state.currentPlayer.visibility.isShowedHumanTrait
                ) { isChecked ->
                    if (isChecked) selectedCount++ else selectedCount--
                    viewmodel.ShowAttributes(
                        state.currentPlayer.visibility.copy(
                            isShowedHumanTrait = isChecked
                        )
                    )
                }
                AttributeCard(
                    label = state.currentPlayer.health,
                    attribute = "Здоров'я",
                    show = state.show,
                    isEnabled = state.currentPlayer.enabled.isEnabledHealth,
                    selectedCount = selectedCount,
                    maxSelection = state.maxAttributesToChoose,
                    isVisible = state.currentPlayer.visibility.isShowedHealth
                ) { isChecked ->
                    if (isChecked) selectedCount++ else selectedCount--

                    viewmodel.ShowAttributes(state.currentPlayer.visibility.copy(isShowedHealth = isChecked))
                }

                AttributeCard(
                    label = state.currentPlayer.hobby,
                    attribute = "Хоббі",
                    show = state.show,
                    isEnabled = state.currentPlayer.enabled.isEnabledHobby,
                    selectedCount = selectedCount,
                    maxSelection = state.maxAttributesToChoose,
                    isVisible = state.currentPlayer.visibility.isShowedHobby
                ) { isChecked ->
                    if (isChecked) selectedCount++ else selectedCount--

                    viewmodel.ShowAttributes(state.currentPlayer.visibility.copy(isShowedHobby = isChecked))
                }

                AttributeCard(
                    label = state.currentPlayer.phobia,
                    attribute = "Фобія",
                    show = state.show,
                    isEnabled = state.currentPlayer.enabled.isEnabledPhobia,
                    selectedCount = selectedCount,
                    maxSelection = state.maxAttributesToChoose,
                    isVisible = state.currentPlayer.visibility.isShowedPhobia
                ) { isChecked ->
                    if (isChecked) selectedCount++ else selectedCount--

                    viewmodel.ShowAttributes(state.currentPlayer.visibility.copy(isShowedPhobia = isChecked))
                }

                AttributeCard(
                    label = state.currentPlayer.baggage,
                    attribute = "Багаж",
                    show = state.show,
                    isEnabled = state.currentPlayer.enabled.isEnabledBaggage,
                    selectedCount = selectedCount,
                    maxSelection = state.maxAttributesToChoose,
                    isVisible = state.currentPlayer.visibility.isShowedBaggage
                ) { isChecked ->
                    if (isChecked) selectedCount++ else selectedCount--

                    viewmodel.ShowAttributes(state.currentPlayer.visibility.copy(isShowedBaggage = isChecked))
                }

                AttributeCard(
                    label = state.currentPlayer.additionalInfo,
                    attribute = "Дод. інфа",
                    show = state.show,
                    isEnabled = state.currentPlayer.enabled.isEnabledAdditionalInfo,
                    selectedCount = selectedCount,
                    maxSelection = state.maxAttributesToChoose,
                    isVisible = state.currentPlayer.visibility.isShowedAdditionalInfo
                ) { isChecked ->
                    if (isChecked) selectedCount++ else selectedCount--

                    viewmodel.ShowAttributes(
                        state.currentPlayer.visibility.copy(
                            isShowedAdditionalInfo = isChecked
                        )
                    )
                }
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = TextFieldBG
                ),
                border = BorderStroke(2.dp, Color.Black),
                onClick = {
                    if (state.show && selectedCount == state.maxAttributesToChoose) {
                        viewmodel.ShowNextPlayer(
                            state.currentPlayer.copy(
                                enabled = Enabled(
                                    isEnabledAge = !state.currentPlayer.visibility.isShowedAge,
                                    isEnabledBody = !state.currentPlayer.visibility.isShowedBody,
                                    isEnabledHumanTrait = !state.currentPlayer.visibility.isShowedHumanTrait,
                                    isEnabledHealth = !state.currentPlayer.visibility.isShowedHealth,
                                    isEnabledHobby = !state.currentPlayer.visibility.isShowedHobby,
                                    isEnabledPhobia = !state.currentPlayer.visibility.isShowedPhobia,
                                    isEnabledBaggage = !state.currentPlayer.visibility.isShowedBaggage,
                                    isEnabledAdditionalInfo = !state.currentPlayer.visibility.isShowedAdditionalInfo,
                                )
                            )
                        )
                        viewmodel.Show()
                        selectedCount = 0
                    } else if (!state.show) {
                        viewmodel.Show()
                    } else {
                        Toast.makeText(
                            context,
                            "Виберіть ${state.maxAttributesToChoose} атрибутів",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }) {
                Text(
                    text = if (!state.show) "Показати характеристики" else "Наступний гравець",
                    color = CustomRed
                )
            }
        }
    }
}

@Composable
fun AttributeCard(
    label: String,
    attribute: String,
    show: Boolean,
    selectedCount: Int,
    maxSelection: Int,
    isEnabled: Boolean,
    isVisible: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {

    // Визначаємо стан картки
    var isSelected by remember { mutableStateOf(isVisible) }

    LaunchedEffect(key1 = show) {
        if (!show) {
            isSelected = false
        }
    }
    // Визначаємо колір фону залежно від того, чи картка заблокована

    val borderColor =
        if (isSelected && isEnabled) Color.Red else if (!isSelected && isEnabled) Color.Black else Color.Transparent
    val backgroundColor = if (!isEnabled) TextFieldBG.copy(0.7f) else TextFieldBG
    val textColor =
        if (isSelected && isEnabled) Color.Red else if (!isSelected && isEnabled) CustomRed else CustomRed.copy(
            0.7f
        )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                enabled = isEnabled && show && (isSelected || selectedCount < maxSelection),
                onClick = {
                    isSelected = !isSelected
                    onCheckedChange(isSelected)
                }
            ),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        border = BorderStroke(1.dp, borderColor)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Відображення атрибута залежно від значення show
            Text(text = "$attribute: ${if (show) label else "..."}", color = textColor)
        }
    }
}
