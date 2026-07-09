package com.example.a30_days_of_movement.model

import androidx.annotation.ArrayRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Movement(
    @StringRes val movementName: Int,
    @ArrayRes val movementCues: Int,
    @DrawableRes val movementImage: Int,
)
