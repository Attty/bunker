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
import kotlin.math.roundToInt

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

    fun CalculationMaxAttributesToChoose() {
        when (_state.value.maxLoops) {
            1 -> {
                when (_state.value.currentLoop) {
                    1 -> {
                        _state.value = state.value.copy(
                            maxAttributesToChoose = 1
                        )
                    }
                }
            }
            2 -> {
                when (_state.value.currentLoop) {
                    1 -> {
                        _state.value = state.value.copy(
                            maxAttributesToChoose = 1
                        )
                    }

                    2 -> {
                        _state.value = state.value.copy(
                            maxAttributesToChoose = 2
                        )
                    }
                }
            }

            3 -> {
                when (_state.value.currentLoop) {
                    1 -> {
                        _state.value = state.value.copy(
                            maxAttributesToChoose = 1
                        )
                    }

                    2 -> {
                        _state.value = state.value.copy(
                            maxAttributesToChoose = 2
                        )
                    }

                    3 -> {
                        _state.value = state.value.copy(
                            maxAttributesToChoose = 2
                        )
                    }
                }
            }

            4 -> {
                when (_state.value.currentLoop) {
                    1 -> {
                        _state.value = state.value.copy(
                            maxAttributesToChoose = 1
                        )
                    }

                    2 -> {
                        _state.value = state.value.copy(
                            maxAttributesToChoose = 2
                        )
                    }

                    3 -> {
                        _state.value = state.value.copy(
                            maxAttributesToChoose = 2
                        )
                    }

                    4 -> {
                        _state.value = state.value.copy(
                            maxAttributesToChoose = 1
                        )
                    }
                }

            }

            5 -> {
                when (_state.value.currentLoop) {
                    1 -> {
                        _state.value = state.value.copy(
                            maxAttributesToChoose = 1
                        )
                    }

                    2 -> {
                        _state.value = state.value.copy(
                            maxAttributesToChoose = 2
                        )
                    }

                    3 -> {
                        _state.value = state.value.copy(
                            maxAttributesToChoose = 1
                        )
                    }

                    4 -> {
                        _state.value = state.value.copy(
                            maxAttributesToChoose = 1
                        )
                    }

                    5 -> {
                        _state.value = state.value.copy(
                            maxAttributesToChoose = 1
                        )
                    }
                }
            }

            6 -> {
                when (_state.value.currentLoop) {
                    1 -> {
                        _state.value = state.value.copy(
                            maxAttributesToChoose = 1
                        )
                    }

                    2 -> {
                        _state.value = state.value.copy(
                            maxAttributesToChoose = 1
                        )
                    }

                    3 -> {
                        _state.value = state.value.copy(
                            maxAttributesToChoose = 1
                        )
                    }

                    4 -> {
                        _state.value = state.value.copy(
                            maxAttributesToChoose = 1
                        )
                    }

                    5 -> {
                        _state.value = state.value.copy(
                            maxAttributesToChoose = 1
                        )
                    }

                    6 -> {
                        _state.value = state.value.copy(
                            maxAttributesToChoose = 1
                        )
                    }
                }
            }
        }
        println(_state.value.maxAttributesToChoose)
    }

    fun CalculationMaxLoops(playerCount: Int) {
        _state.value = state.value.copy(
            maxLoops = (playerCount.toFloat() / 2).roundToInt()
        )
    }

    fun ShowFirstPerson() {

        val currentState = _state.value
        val alivePlayers = filterAlivePlayers(currentState.list)

        if (alivePlayers.isNotEmpty()) {
            // Якщо знайдено живих гравців, оновити стан на першого з них
            _state.value = currentState.copy(
                currentPlayer = alivePlayers.first(),
                indexPlayer = currentState.list.indexOf(alivePlayers.first())
            )
        } else {
            // Якщо всі гравці мертві, можна встановити, що цикл завершено
            _state.value = currentState.copy(
                isLoopEnd = true,
            )
        }
    }

    fun Show() {
        _state.value = state.value.copy(
            show = !_state.value.show
        )
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
            this[currentState.indexPlayer] =
                currentPlayer // новий об'єкт, який потрібно вставити
        }

        val alivePlayers = filterAlivePlayers(updatedList)
        val currentIndex = alivePlayers.indexOfFirst { it == currentPlayer }

        if (currentIndex != -1 && currentIndex < alivePlayers.size - 1) {
            val nextPlayer = alivePlayers[currentIndex + 1]
            val newIndex = updatedList.indexOf(nextPlayer)

            _state.value = currentState.copy(
                list = updatedList,
                currentPlayer = nextPlayer,
                indexPlayer = newIndex
            )
        } else {
            _state.value = currentState.copy(
                list = updatedList,
                isLoopEnd = true
            )
        }

    }

    fun LoopEnd() {
        _state.value = state.value.copy(
            isLoopEnd = false,
            kickedPlayer = Model(
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
        CalculationMaxAttributesToChoose()
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
                kickedPlayer = personRemoved,
                currentLoop = currentState.currentLoop + 1
            )
            ShowFirstPerson()
        } else if (currentState.currentLoop == currentState.maxLoops) {
            _state.value = currentState.copy(
                list = updatedList,
                isLoopEnd = false,
                isGameFinished = true
            )
        }
    }


    fun filterAlivePlayers(
        players: List<Model>

    )
            : List<Model> {
        return players.filter { !it.isDead }
    }


}