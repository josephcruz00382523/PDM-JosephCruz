package com.pdm0126.laboratorio3.Navagation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoutes : NavKey {
    @Serializable
    data object HomeScreen : NavKey

    @Serializable
    data object SensorScreen : NavKey

}