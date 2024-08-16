package com.example.bunker.presentation.distribute_attributes_screen.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.bunker.DistributeAttributesScreenObj
import com.example.bunker.domain.Model
import com.example.bunker.domain.util.util
import com.example.bunker.presentation.distribute_attributes_screen.state.AttributesScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AttributesScreenViewmodel @Inject constructor() : ViewModel() {
    private val _state = mutableStateOf(AttributesScreenState())
    val state: State<AttributesScreenState> = _state



    fun DistributeAttributes(list: List<String>) {
        _state.value = state.value.copy(
            list = list.map { name ->
                val sex = util.sexList.random()
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

}