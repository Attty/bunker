package com.example.bunker.presentation.distribute_attributes_screen.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.bunker.domain.Enabled
import com.example.bunker.domain.Model
import com.example.bunker.presentation.distribute_attributes_screen.viewmodel.AttributesScreenViewmodel

@Composable
fun DistributeAttributesScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    list: List<String>,
    viewmodel: AttributesScreenViewmodel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewmodel.CalculationMaxLoops(list.size)
        viewmodel.DistributeAttributes(list)
        viewmodel.ShowFirstPerson()
    }
    var choosenPerson by remember {
        mutableStateOf(Model(
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
        ))
    }
    val state = viewmodel.state.value
    if (state.isLoopEnd) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Круг закінчився, виберіть кого хочете кікнуть")
            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                itemsIndexed(viewmodel.filterAlivePlayers(state.list)) { index, item ->

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { choosenPerson = item }
                            .padding(16.dp),
                        border = if(choosenPerson == item){
                            BorderStroke(1.dp, Color.Red)
                        }else {
                            BorderStroke(0.dp, Color.Red)
                        }
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(item.name)
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Text(text = item.virtualName)
                                Text(
                                    text = "Стать : ${
                                        if (item.visibility.isShowedSex) {
                                            item.sex
                                        } else "..."
                                    }"
                                )

                                Text(
                                    text = "Вік : ${
                                        if (item.visibility.isShowedAge) {
                                            item.age.toString()
                                        } else "..."
                                    }"
                                )

                                Text(
                                    text = "Професія : ${
                                        if (item.visibility.isShowedJob) {
                                            item.job
                                        } else "..."
                                    }"
                                )

                                Text(
                                    text = "Тип статури : ${
                                        if (item.visibility.isShowedBody) {
                                            item.body
                                        } else "..."
                                    }"
                                )

                                Text(
                                    text = "Риса характеру : ${
                                        if (item.visibility.isShowedHumanTrait) {
                                            item.humanTrait
                                        } else "..."
                                    }"
                                )

                                Text(
                                    text = "Стан здоров'я : ${
                                        if (item.visibility.isShowedHealth) {
                                            item.health
                                        } else "..."
                                    }"
                                )

                                Text(
                                    text = "Хобі : ${
                                        if (item.visibility.isShowedHobby) {
                                            item.hobby
                                        } else "..."
                                    }"
                                )

                                Text(
                                    text = "Фобія : ${
                                        if (item.visibility.isShowedPhobia) {
                                            item.phobia
                                        } else "..."
                                    }"
                                )

                                Text(
                                    text = "Багаж : ${
                                        if (item.visibility.isShowedBaggage) {
                                            item.baggage
                                        } else "..."
                                    }"
                                )

                                Text(
                                    text = "Додаткова інформація : ${
                                        if (item.visibility.isShowedAdditionalInfo) {
                                            item.additionalInfo
                                        } else "..."
                                    }"
                                )


                            }
                        }
                    }

                }

            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { viewmodel.NewLoop(choosenPerson) }) {
                Text(text = "Next loop")
            }

        }
    } else {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = state.currentPlayer.name)

            Column(
                Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Row(Modifier.fillMaxWidth()) {
                    Checkbox(
                        checked = true,
                        onCheckedChange = { },
                        enabled = false
                    )
                    Text(text = state.currentPlayer.virtualName)
                }
                AttributeRow(
                    label = state.currentPlayer.sex,
                    isVisible = state.currentPlayer.visibility.isShowedSex,
                    isLocked = state.currentPlayer.enabled.isEnabledSex
                ) { isChecked ->
                    viewmodel.ShowAttributes(state.currentPlayer.visibility.copy(isShowedSex = isChecked))
                }

                AttributeRow(
                    label = state.currentPlayer.age.toString(),
                    isVisible = state.currentPlayer.visibility.isShowedAge,
                    isLocked = state.currentPlayer.enabled.isEnabledAge
                ) { isChecked ->
                    viewmodel.ShowAttributes(state.currentPlayer.visibility.copy(isShowedAge = isChecked))
                }

                AttributeRow(
                    label = state.currentPlayer.job,
                    isVisible = state.currentPlayer.visibility.isShowedJob,
                    isLocked = state.currentPlayer.enabled.isEnabledJob
                ) { isChecked ->
                    viewmodel.ShowAttributes(state.currentPlayer.visibility.copy(isShowedJob = isChecked))
                }

                AttributeRow(
                    label = state.currentPlayer.body,
                    isVisible = state.currentPlayer.visibility.isShowedBody,
                    isLocked = state.currentPlayer.enabled.isEnabledBody
                ) { isChecked ->
                    viewmodel.ShowAttributes(state.currentPlayer.visibility.copy(isShowedBody = isChecked))
                }

                AttributeRow(
                    label = state.currentPlayer.humanTrait,
                    isVisible = state.currentPlayer.visibility.isShowedHumanTrait,
                    isLocked = state.currentPlayer.enabled.isEnabledHumanTrait
                ) { isChecked ->
                    viewmodel.ShowAttributes(state.currentPlayer.visibility.copy(isShowedHumanTrait = isChecked))
                }

                AttributeRow(
                    label = state.currentPlayer.health,
                    isVisible = state.currentPlayer.visibility.isShowedHealth,
                    isLocked = state.currentPlayer.enabled.isEnabledHealth
                ) { isChecked ->
                    viewmodel.ShowAttributes(state.currentPlayer.visibility.copy(isShowedHealth = isChecked))
                }

                AttributeRow(
                    label = state.currentPlayer.hobby,
                    isVisible = state.currentPlayer.visibility.isShowedHobby,
                    isLocked = state.currentPlayer.enabled.isEnabledHobby
                ) { isChecked ->
                    viewmodel.ShowAttributes(state.currentPlayer.visibility.copy(isShowedHobby = isChecked))
                }

                AttributeRow(
                    label = state.currentPlayer.phobia,
                    isVisible = state.currentPlayer.visibility.isShowedPhobia,
                    isLocked = state.currentPlayer.enabled.isEnabledPhobia
                ) { isChecked ->
                    viewmodel.ShowAttributes(state.currentPlayer.visibility.copy(isShowedPhobia = isChecked))
                }

                AttributeRow(
                    label = state.currentPlayer.baggage,
                    isVisible = state.currentPlayer.visibility.isShowedBaggage,
                    isLocked = state.currentPlayer.enabled.isEnabledBaggage
                ) { isChecked ->
                    viewmodel.ShowAttributes(state.currentPlayer.visibility.copy(isShowedBaggage = isChecked))
                }

                AttributeRow(
                    label = state.currentPlayer.additionalInfo,
                    isVisible = state.currentPlayer.visibility.isShowedAdditionalInfo,
                    isLocked = state.currentPlayer.enabled.isEnabledAdditionalInfo
                ) { isChecked ->
                    viewmodel.ShowAttributes(
                        state.currentPlayer.visibility.copy(
                            isShowedAdditionalInfo = isChecked
                        )
                    )
                }
            }
            Button(onClick = {
                viewmodel.ShowNextPlayer(
                    state.currentPlayer.copy(
                        enabled = Enabled(
                            isEnabledSex = !state.currentPlayer.visibility.isShowedSex,
                            isEnabledAge = !state.currentPlayer.visibility.isShowedAge,
                            isEnabledJob = !state.currentPlayer.visibility.isShowedJob,
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
            }) {

            }
        }
    }
}

@Composable
fun AttributeRow(
    label: String,
    isVisible: Boolean,
    isLocked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(Modifier.fillMaxWidth()) {
        Checkbox(
            checked = isVisible,
            onCheckedChange = onCheckedChange,
            enabled = isLocked
        )
        Text(text = label)
    }
}
