package com.example.tracker_presentation.tracker_overview.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import com.example.core_ui.CarbColor
import com.example.core_ui.FatColor
import com.example.core_ui.ProteinColor

@Composable
fun NutrientsBar(
    carbs: Int,
    protein: Int,
    fat: Int,
    calories: Int,
    calorieGoal: Int,
    modifier: Modifier = Modifier
) {
    val background = MaterialTheme.colors.background
    val caloriesExceedColor = MaterialTheme.colors.error
    val carbWidthRatio = remember {
        Animatable(0f)
    }
    val proteinWidthRatio = remember {
        Animatable(0f)
    }
    val fatWidthRatio = remember {
        Animatable(0f)
    }

    LaunchedEffect(carbs) {
        carbWidthRatio.animateTo((carbs * 4f) / calorieGoal)
    }
    LaunchedEffect(protein) {
        proteinWidthRatio.animateTo((protein * 4f) / calorieGoal)
    }
    LaunchedEffect(fat) {
        fatWidthRatio.animateTo((fat * 9f) / calorieGoal)
    }

    Canvas(modifier = modifier) {
        if(calories <= calorieGoal) {
            val carbsWidth = carbWidthRatio.value * size.width
            val proteinWidth = proteinWidthRatio.value * size.width
            val fatWidth = fatWidthRatio.value * size.width

            drawRoundRect(
                color = background,
                size = size,
                cornerRadius = CornerRadius(100f)
            )
            drawRoundRect(
                color = FatColor,
                size = Size(
                    carbsWidth + proteinWidth + fatWidth,
                    size.height
                ),
                cornerRadius = CornerRadius(100f)
            )
            drawRoundRect(
                color = ProteinColor,
                size = Size(
                    carbsWidth + proteinWidth,
                    size.height
                ),
                cornerRadius = CornerRadius(100f)
            )
            drawRoundRect(
                color = CarbColor,
                size = Size(
                    carbsWidth,
                    size.height
                ),
                cornerRadius = CornerRadius(100f)
            )
        } else {
            drawRoundRect(
                color = caloriesExceedColor,
                size = size,
                cornerRadius = CornerRadius(100f)
            )
        }
    }
}