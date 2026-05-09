package com.pdm0126.laboratorio3.Screens


import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun sensors(sensorName: String, onBack: () -> Unit) {
    val context = LocalContext.current


    var luxValue by remember { mutableStateOf(0f) }
    var accelX by remember { mutableStateOf(0f) }
    var accelY by remember { mutableStateOf(0f) }
    var accelZ by remember { mutableStateOf(0f) }

    val sensorInfo = mapOf(
        "Acelerómetro" to "Mide la aceleración y los cambios de movimiento en los ejes X, Y y Z.",
        "Magnetómetro" to "Funciona como brújula digital detectando el campo magnético terrestre.",
        "Barómetro" to "Mide la presión atmosférica y permite calcular la altitud aproximada.",
        "Sensor de Huellas Dactilares" to "Captura y verifica huellas dactilares para autenticación biométrica.",
        "Sensor de Ritmo Cardíaco" to "Detecta el ritmo cardíaco del usuario. Disponible en modelos premium.",
        "Sensor de Temperatura" to "Monitorea la temperatura del dispositivo o del entorno.",
        "Sensor de Luz" to "Mide la intensidad de luz ambiental para ajustar el brillo de la pantalla.",
        "Giroscopio" to "Detecta la orientación y rotación del dispositivo en el espacio.",
        "Sensor de Proximidad" to "Detecta si hay objetos cerca del dispositivo, como la cara del usuario al llamar."
    )

    val description = sensorInfo[sensorName] ?: "Información no disponible."


    DisposableEffect(sensorName) {
        val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent) {
                when (event.sensor.type) {
                    Sensor.TYPE_LIGHT -> luxValue = event.values[0]
                    Sensor.TYPE_ACCELEROMETER -> {
                        accelX = event.values[0]
                        accelY = event.values[1]
                        accelZ = event.values[2]
                    }
                }
            }
            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }

        when (sensorName) {
            "Sensor de Luz" -> {
                sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)?.let {
                    sensorManager.registerListener(listener, it, SensorManager.SENSOR_DELAY_UI)
                }
            }
            "Acelerómetro" -> {
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.let {
                    sensorManager.registerListener(listener, it, SensorManager.SENSOR_DELAY_UI)
                }
            }
        }

        onDispose {
            sensorManager.unregisterListener(listener)
        }
    }


    val bgColor by animateColorAsState(
        targetValue = when {
            sensorName != "Sensor de Luz" -> Color(0xFF1A1A2E)
            luxValue < 10f   -> Color(0xFF0A0A0A)
            luxValue < 200f  -> Color(0xFF2E2E2E)
            luxValue < 1000f -> Color(0xFFAAAAAA)
            else             -> Color(0xFFF5F5F5)
        },
        animationSpec = tween(800),
        label = "bgColor"
    )

    val textColor by animateColorAsState(
        targetValue = when {
            sensorName != "Sensor de Luz" -> Color.White
            luxValue < 200f -> Color.White
            else            -> Color(0xFF1A1A1A)
        },
        animationSpec = tween(800),
        label = "textColor"
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(sensorName, color = textColor) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver",
                            tint = textColor
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = bgColor
                )
            )
        },
        containerColor = bgColor
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = sensorName,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )

            Spacer(modifier = Modifier.height(16.dp))


            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = textColor.copy(alpha = 0.1f)
                )
            ) {
                Text(
                    text = description,
                    modifier = Modifier.padding(20.dp),
                    fontSize = 16.sp,
                    color = textColor.copy(alpha = 0.8f),
                    lineHeight = 24.sp
                )
            }

            Spacer(modifier = Modifier.height(32.dp))


            when (sensorName) {
                "Sensor de Luz" -> {
                    Text(
                        text = "${luxValue.toInt()} lux",
                        fontSize = 64.sp,
                        fontWeight = FontWeight.Black,
                        color = textColor
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = when {
                            luxValue < 10f   -> "Muy oscuro"
                            luxValue < 200f  -> "Luz tenue"
                            luxValue < 1000f -> "Luz normal"
                            else             -> "Muy brillante"
                        },
                        fontSize = 20.sp,
                        color = textColor.copy(alpha = 0.7f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }

                "Acelerómetro" -> {
                    listOf(
                        "X" to accelX,
                        "Y" to accelY,
                        "Z" to accelZ
                    ).forEach { (axis, value) ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFF16213E)
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Eje $axis",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF4F8EF7)
                                )
                                Text(
                                    text = "%.2f m/s²".format(value),
                                    fontSize = 18.sp,
                                    color = Color.White
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}