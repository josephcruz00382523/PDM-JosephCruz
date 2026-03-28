package com.example.laboratorio2

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.laboratorio2.ui.theme.Laboratorio2Theme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.BlendMode.Companion.Color
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Laboratorio2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(

                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}



@Composable
fun Surface(modifier: Modifier = Modifier) {

    val nombre: MutableState<String> = remember { mutableStateOf("") }
    val lista = remember { mutableStateListOf<String>() }
    val keyboardController = LocalSoftwareKeyboardController.current // no es copy paste solo me estaba estresando que se quede arriba el keyboard

    Column(Modifier.fillMaxWidth()
        .background(Color(0xFFF5E6C8)),
        horizontalAlignment = Alignment.CenterHorizontally) {


        Column(
            Modifier
                .fillMaxWidth()
                .weight(2F, true)
                .windowInsetsPadding(WindowInsets.statusBars)//tambien me molestaba
        ) {

            TextField(
                value = nombre.value,
                onValueChange = { nombre.value = it },
                placeholder = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor   = Color(0xFFEDD9A3),
                    unfocusedContainerColor = Color(0xFFEDD9A3),
                    focusedTextColor        = Color(0xFF1B2A4A)

                )

            )

            Spacer(Modifier.height(8.dp))

            Button(onClick = {
                if (nombre.value.isNotBlank()) {
                    lista.add(nombre.value)
                    nombre.value = ""
                    keyboardController?.hide()
                }
            },
                modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF1B2A4A),
                    contentColor   = Color(0xFFFFFFFF)) ){
                Text("Guardar")
            }
        }

        Row(
            Modifier.fillMaxWidth().weight(2F, true),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Listado de nombres y posicion en la lista",
                color = Color(50, 50, 50),
                modifier = Modifier.weight(1F).padding(end = 8.dp))

            Button(onClick = { lista.clear() },
                colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1B2A4A),
                contentColor   = Color(0xFFFFFFFF)) ) {
                Text(text = "Limpiar lista")
            }
        }

        Box(Modifier.fillMaxWidth().weight(4F, true)) {
            LazyColumn(Modifier.fillMaxWidth()
                .fillMaxHeight()
                .border(2.dp,Color(0xFFE8593C), RoundedCornerShape(8.dp))) {

                itemsIndexed(lista) { index, item ->
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = item,
                            color = Color(50, 50, 50)
                        )

                        Text(text = (index + 1).toString(),
                            color = Color(50, 50, 50)
                        )
                    }
                }
            }
        }

    }
}



