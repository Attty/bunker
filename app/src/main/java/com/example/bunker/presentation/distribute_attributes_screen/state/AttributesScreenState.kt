package com.example.bunker.presentation.distribute_attributes_screen.state

import com.example.bunker.domain.Model

data class AttributesScreenState(
    val list: List<Model> = emptyList()
)