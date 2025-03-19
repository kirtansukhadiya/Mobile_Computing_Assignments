package com.example.sensorapp

import android.os.Bundle
import android.util.Log
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import com.example.sensorapp.ui.theme.SensorAppTheme
import java.io.File

class MainActivity : ComponentActivity(), SensorEventListener {
    private lateinit var sensorManager: SensorManager
    private var lightSensor: Sensor? = null
    private var proximitySensor: Sensor? = null
    private var magnetometer: Sensor? = null

    // Use mutableStateOf to update the UI in Jetpack Compose
    private val _lightLevel = mutableStateOf("Light Level: Unknown")
    private val _proximityLevel = mutableStateOf("Proximity: Unknown")
    private val _magnetometerAvailable = mutableStateOf("Magnetometer: Unknown")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)

        _magnetometerAvailable.value = if (magnetometer != null) "Magnetometer: Available" else "Magnetometer: Not Available"

        setContent {
            SensorAppTheme {
                Scaffold { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        color = Color(0xFFbfe3d0)
                    ) {
                        SensorInfoScreen(
                            lightLevel = _lightLevel.value,
                            proximityLevel = _proximityLevel.value,
                            magnetometerInfo = _magnetometerAvailable.value
                        )
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        lightSensor?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL) }
        proximitySensor?.let { sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL) }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let { sensorEvent ->
            when (sensorEvent.sensor.type) {
                Sensor.TYPE_LIGHT -> {
                    _lightLevel.value = "Light Level: ${sensorEvent.values[0]} lx"
                }
                Sensor.TYPE_PROXIMITY -> {
                    val proximityValue = sensorEvent.values[0]
                    _proximityLevel.value = "Proximity: $proximityValue cm" // Show exact value

                    Log.d("ProximitySensor", "Proximity Value: $proximityValue cm")
                }
            }
        }
    }


    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}




@Composable
fun SensorInfoScreen(lightLevel: String, proximityLevel: String, magnetometerInfo: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = lightLevel,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = proximityLevel,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = magnetometerInfo,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Black
        )
    }
}

