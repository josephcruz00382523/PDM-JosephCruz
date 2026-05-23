package com.pdm0126.laboratorio4.Navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface AppRoutes : NavKey {

    @Serializable
    object Home : AppRoutes

    @Serializable
    object Task : AppRoutes
}
