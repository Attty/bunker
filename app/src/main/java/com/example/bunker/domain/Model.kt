package com.example.bunker.domain

data class Visibility(
    val isShowedSex: Boolean = false,
    val isShowedAge: Boolean = false,
    val isShowedJob: Boolean = false,
    val isShowedBody: Boolean = false,
    val isShowedHumanTrait: Boolean = false,
    val isShowedHealth: Boolean = false,
    val isShowedHobby: Boolean = false,
    val isShowedPhobia: Boolean = false,
    val isShowedBaggage: Boolean = false,
    val isShowedAdditionalInfo: Boolean = false
)
data class Enabled(
    val isEnabledSex: Boolean = true,
    val isEnabledAge: Boolean = true,
    val isEnabledJob: Boolean = true,
    val isEnabledBody: Boolean = true,
    val isEnabledHumanTrait: Boolean = true,
    val isEnabledHealth: Boolean = true,
    val isEnabledHobby: Boolean = true,
    val isEnabledPhobia: Boolean = true,
    val isEnabledBaggage: Boolean = true,
    val isEnabledAdditionalInfo: Boolean = true
)

data class Model(
    val name: String,
    val virtualName: String,
    val sex: String,
    val age: Int,
    val job: String,
    val body: String,
    val humanTrait: String,
    val health: String,
    val hobby: String,
    val phobia: String,
    val baggage: String,
    val additionalInfo: String,
    val visibility: Visibility = Visibility(),
    val enabled: Enabled = Enabled(),
    val isDead: Boolean
)