package dev.pablorjd.rickandmorty

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import dev.pablorjd.rickandmorty.presentation.RickListScreen
import dev.pablorjd.rickandmorty.ui.theme.RickAndMortyTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var proximitySensor: Sensor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)

        // Verificar si el sensor de proximidad está disponible
        if (proximitySensor == null) {
            Toast.makeText(this, "El sensor de proximidad no está disponible", Toast.LENGTH_SHORT).show()
        } else {
            // Registrar el listener para el sensor de proximidad
            sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL)
        }

        enableEdgeToEdge()
        setContent {
            RickAndMortyTheme {
                RickListScreen()
            }
        }


    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_PROXIMITY) {
            val distance = event.values[0]
            // Aquí puedes manejar la distancia detectada por el sensor
            Toast.makeText(this, "Distancia: $distance cm", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        sensor.let {
            Toast.makeText(this, "Accuracy changed to $accuracy", Toast.LENGTH_SHORT).show()
        }
    }
}

