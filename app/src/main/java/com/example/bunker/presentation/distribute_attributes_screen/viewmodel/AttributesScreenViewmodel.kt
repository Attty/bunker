package com.example.bunker.presentation.distribute_attributes_screen.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bunker.domain.Model
import com.example.bunker.domain.Visibility
import com.example.bunker.domain.util.util
import com.example.bunker.presentation.distribute_attributes_screen.state.AttributesScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.max

@HiltViewModel
class AttributesScreenViewmodel @Inject constructor() : ViewModel() {
    private val _state = mutableStateOf(AttributesScreenState())
    val state: State<AttributesScreenState> = _state
    fun DistributeAttributes(list: List<String>) {
        _state.value = state.value.copy(
            list = list.map { name ->
                val sex = util.sexList.random()
                val virtualName = if (sex == "Чоловік") {
                    util.virtualNameList_man.random()
                } else {
                    util.virtualNameList_woman.random()
                }
                val age = util.ageList.random()
                val job = if (age < 18) {
                    "Без професії"
                } else {
                    util.jobList.random()
                }
                val body = if (sex == "Чоловік") {
                    util.bodyList_man.random()
                } else {
                    util.bodyList_woman.random()
                }
                val humanTrait = if (sex == "Чоловік") {
                    util.humanTrait_man.random()
                } else {
                    util.humanTrait_woman.random()
                }
                val health = util.healthList.random()
                val hobby = util.hobbyList.random()
                val phobia = util.phobiaList.random()
                val baggage = util.baggageList.random()
                val additionalInfo = util.additionalInfoList.random()
                Model(
                    name = name,
                    virtualName = virtualName,
                    sex = sex,
                    age = age,
                    job = job,
                    body = body,
                    humanTrait = humanTrait,
                    health = health,
                    hobby = hobby,
                    phobia = phobia,
                    baggage = baggage,
                    additionalInfo = additionalInfo,
                    isDead = false
                )
            }
        )
    }

    fun CalculationMaxLoops(playerCount: Int) {
        _state.value = state.value.copy(
            maxLoops = playerCount / 2
        )
    }

    fun ShowFirstPerson() {
        val currentState = _state.value
        var newIndex = 0

        // Знайти першого живого гравця
        while (newIndex < currentState.list.size && currentState.list[newIndex].isDead) {
            newIndex++
        }

        if (newIndex < currentState.list.size) {
            // Якщо знайдено живого гравця, оновити стан
            _state.value = currentState.copy(
                currentPlayer = currentState.list[newIndex],
                indexPlayer = newIndex
            )
        } else {
            // Якщо всі гравці мертві, можна встановити, що цикл завершено
            _state.value = currentState.copy(
                isLoopEnd = true
            )
        }
    }


    fun ShowAttributes(showedAttributes: Visibility) {
        _state.value = state.value.copy(
            currentPlayer = _state.value.list[_state.value.indexPlayer].copy(
                visibility = showedAttributes
            )
        )
    }

    fun ShowNextPlayer(currentPlayer: Model) {
        val currentState = _state.value
        val updatedList = currentState.list.toMutableList().apply {
            this[currentState.indexPlayer] = currentPlayer // новий об'єкт, який потрібно вставити
        }

        var newIndex = currentState.indexPlayer

        // Переміщення до наступного гравця, якщо поточний гравець мертвий
        while (newIndex < updatedList.size - 1 && updatedList[newIndex].isDead) {
            newIndex++
        }

        if (newIndex < updatedList.size - 1) {
            newIndex++ // Переміщення до наступного живого гравця

            _state.value = currentState.copy(
                list = updatedList,
                currentPlayer = updatedList[newIndex],
                indexPlayer = newIndex
            )
        } else {
            _state.value = currentState.copy(
                list = updatedList,
                isLoopEnd = true
            )
        }
    }

    fun NewLoop(
        personRemoved: Model
    ) {
        val currentState = _state.value
        val updatedList = currentState.list.map { player ->
            if (player == personRemoved) {
                player.copy(isDead = true)
            } else {
                player
            }
        }

        if (currentState.currentLoop != currentState.maxLoops) {
            _state.value = currentState.copy(
                list = updatedList,
                indexPlayer = 0,
                isLoopEnd = false,
                currentLoop = currentState.currentLoop + 1
            )
            ShowFirstPerson()
        } else if (currentState.currentLoop == currentState.maxLoops) {
            _state.value = currentState.copy(
                list = updatedList,
                indexPlayer = 0,
                isLoopEnd = false,
                currentLoop = currentState.currentLoop + 1
            )
        }
    }


    fun filterAlivePlayers(players: List<Model>): List<Model> {
        return players.filter { !it.isDead }
    }



}