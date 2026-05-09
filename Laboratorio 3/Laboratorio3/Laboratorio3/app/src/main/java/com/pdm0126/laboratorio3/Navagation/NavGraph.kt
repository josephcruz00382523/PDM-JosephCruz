package com.pdm0126.laboratorio3.Navagation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdm0126.laboratorio3.Screens.Home
import com.pdm0126.laboratorio3.Screens.sensors




@Composable
fun NavigationGraph(){
    val backStack = rememberNavBackStack(AppRoutes.HomeScreen)


    NavDisplay(
        backStack = backStack,
        onBack = {backStack.removeLastOrNull()},
        entryProvider = entryProvider {

            entry<AppRoutes.HomeScreen>{
                Home(
                    onSensorClick = { sensorName ->
                        backStack.add(AppRoutes.SensorScreen(sensorName))
                    }
                )
            }

            entry<AppRoutes.SensorScreen> { route ->
                sensors(
                    sensorName = route.sensorName,
                    onBack = { backStack.removeLastOrNull() }
                )
            }
        }

    )

}



