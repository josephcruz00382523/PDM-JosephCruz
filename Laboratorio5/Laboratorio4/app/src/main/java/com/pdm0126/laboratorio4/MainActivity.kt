package com.pdm0126.laboratorio4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.pdm0126.laboratorio4.Data.TaskRepository
import com.pdm0126.laboratorio4.Navigation.NavGraph
import com.pdm0126.laboratorio4.screen.GeneralViewModel
import com.pdm0126.laboratorio4.ui.theme.Laboratorio4Theme

class MainActivity : ComponentActivity() {

    private val repository by lazy {
        TaskRepository(InitDatabase.database.taskDao())
    }

    private val generalViewModel: GeneralViewModel by viewModels {
        GeneralViewModel.factory(repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio4Theme {
                NavGraph(generalViewModel = generalViewModel)
            }
        }
    }
}
