package com.example.bunker.presentation.newgame_screen.viewmodel

import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bunker.presentation.newgame_screen.state.NewgameScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewgameScreenViewmodel @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(NewgameScreenState())
    val state: State<NewgameScreenState> = _state

    fun addItemBeforeLast(newItem: String) {
        _state.value = state.value.copy(
            list = if (state.value.list.size > 1) {
                // Додаємо елемент перед останнім
                state.value.list.dropLast(1) + newItem + state.value.list.last()
            } else {
                // Якщо в списку лише один елемент або список порожній
                state.value.list + newItem
            }
        )
    }

    fun updateName(index: Int, newName: String) {
        _state.value = state.value.copy(
            list = state.value.list.mapIndexed { i, item ->
                if (i == index) newName else item
            }
        )
    }
    fun removeItem(index: Int) {
        if (index in state.value.list.indices) {
            val newList = state.value.list.toMutableList()
            newList.removeAt(index)
            println(newList)
            _state.value = state.value.copy(list = newList)
        }
    }

    fun removeLastElement(): MutableList<String> {
        return if (_state.value.list.isNotEmpty()) {
            _state.value.list.subList(0, _state.value.list.size - 1).toMutableList()
        } else {
            _state.value.list.toMutableList()
        }
    }



}