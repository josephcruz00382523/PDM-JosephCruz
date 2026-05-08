package com.pdm0126.laboratorio3.Navagation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdm0126.laboratorio3.Screens.Home


@Composable
fun NavigationGraph(){
    val backStack = rememberNavBackStack(AppRoutes.HomeScreen)


    NavDisplay(
        backStack = backStack,
        onBack = {backStack.removeLastOrNull()},
        entryProvider = entryProvider {

            entry<AppRoutes.HomeScreen>{
                Home(
                    onSensorclick = {backStack.add(AppRoutes.SensorScreen)}
                )
            }

            entry<AppRoutes.SensorScreen>{


            }
        }

    )

}

