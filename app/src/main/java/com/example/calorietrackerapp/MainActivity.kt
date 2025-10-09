package com.example.calorietrackerapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import com.example.calorietrackerapp.ui.theme.CalorieTrackerAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalorieTrackerAppTheme {

                Text(
                    "Hello, CalorieTrackerApp"
                )
            }
        }
    }
}