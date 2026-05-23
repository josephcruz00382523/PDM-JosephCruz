package com.pdm0126.laboratorio4.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdm0126.laboratorio4.screen.GeneralViewModel
import com.pdm0126.laboratorio4.screen.Home
import com.pdm0126.laboratorio4.screen.TaskScreen

@Composable
fun NavGraph() {
    val backStack = rememberNavBackStack(AppRoutes.Home)
    val generalViewModel: GeneralViewModel = viewModel()

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<AppRoutes.Home> {
                Home(
                    viewModel = generalViewModel,
                    onNavigateToTask = { backStack.add(AppRoutes.Task) }
                )
            }
            entry<AppRoutes.Task> {
                TaskScreen(
                    onBackClick = { backStack.removeLastOrNull() },
                    generalViewModel = generalViewModel
                )
            }
        }
    )
}
