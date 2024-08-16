package com.example.bunker.presentation.distribute_attributes_screen.state

import android.text.BoringLayout
import com.example.bunker.domain.Model
import com.example.bunker.domain.Visibility

data class AttributesScreenState(
    val list: List<Model> = emptyList(),
    val currentPlayer: Model = Model(
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
    ),
    val indexPlayer: Int = 0,
    val isLoopEnd: Boolean = false,
    val maxLoops: Int = 0,
    val currentLoop: Int = 1,
    val isGameFinished: Boolean = false

)