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

    fun addItem(newItem: String) {
        _state.value = state.value.copy(
            list = state.value.list + newItem
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
}