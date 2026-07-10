package com.example.a30_days_of_movement.model

import androidx.annotation.ArrayRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Movement(
    @StringRes val name: Int,
    @ArrayRes val cues: Int,
    @DrawableRes val image: Int,
)
