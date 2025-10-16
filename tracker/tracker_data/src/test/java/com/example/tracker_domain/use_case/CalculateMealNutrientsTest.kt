package com.example.tracker_domain.use_case

import com.example.core.domain.model.ActivityLevel
import com.example.core.domain.model.Gender
import com.example.core.domain.model.GoalType
import com.example.core.domain.model.UserInfo
import com.example.core.domain.preferences.Preferences
import com.example.tracker_domain.model.MealType
import com.example.tracker_domain.model.TrackedFood
import com.google.common.truth.Truth.assertThat
import io.mockk.every
import io.mockk.mockk
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.todayIn
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

class CalculateMealNutrientsTest {

    private lateinit var calculateMealNutrients: CalculateMealNutrients

    @Before
    fun setup() {
        val mockPreferences = mockk<Preferences>(relaxed = true)
        every { mockPreferences.loadUserInfo() } returns UserInfo(
            gender = Gender.Male,
            age = 20,
            weight = 80f,
            height = 180,
            activityLevel = ActivityLevel.Medium,
            goalType = GoalType.KeepWeight,
            carbRatio = 0.4f,
            proteinRatio = 0.3f,
            fatRatio = 0.3f
        )

        calculateMealNutrients = CalculateMealNutrients(mockPreferences)
    }

    @Test
    fun `Calories for breakfast properly calculated`() {
        val trackedFoods = (1..30).map {
            TrackedFood(
                name = "name",
                carbs = Random.nextInt(100),
                protein = Random.nextInt(100),
                fat = Random.nextInt(100),
                mealType = MealType.fromString(
                    listOf("breakfast", "lunch", "dinner", "snack").random()
                ),
                imageUrl = null,
                amount = 100,
                date = Clock.System.todayIn(TimeZone.currentSystemDefault()),
                calories = Random.nextInt(2000)
            )
        }

        val result = calculateMealNutrients(trackedFoods)

        val breakfastCalories = result.mealNutrients.values
            .filter { it.mealType is MealType.Breakfast }
            .sumOf { it.calories }
        val expectedCalories = trackedFoods
            .filter { it.mealType is MealType.Breakfast }
            .sumOf { it.calories }

        assertThat(breakfastCalories).isEqualTo(expectedCalories)
    }

    @Test
    fun `Calories for lunch properly calculated`() {
        val trackedFoods = (1..30).map {
            TrackedFood(
                name = "name",
                carbs = Random.nextInt(100),
                protein = Random.nextInt(100),
                fat = Random.nextInt(100),
                mealType = MealType.fromString(
                    listOf("breakfast", "lunch", "dinner", "snack").random()
                ),
                imageUrl = null,
                amount = 100,
                date = Clock.System.todayIn(TimeZone.currentSystemDefault()),
                calories = Random.nextInt(2000)
            )
        }

        val result = calculateMealNutrients(trackedFoods)

        val lunchCalories = result.mealNutrients.values
            .filter { it.mealType is MealType.Lunch }
            .sumOf { it.calories }
        val expectedCalories = trackedFoods
            .filter { it.mealType is MealType.Lunch }
            .sumOf { it.calories }

        assertThat(lunchCalories).isEqualTo(expectedCalories)
    }

    @Test
    fun `Calories for dinner properly calculated`() {
        val trackedFoods = (1..30).map {
            TrackedFood(
                name = "name",
                carbs = Random.nextInt(100),
                protein = Random.nextInt(100),
                fat = Random.nextInt(100),
                mealType = MealType.fromString(
                    listOf("breakfast", "lunch", "dinner", "snack").random()
                ),
                imageUrl = null,
                amount = 100,
                date = Clock.System.todayIn(TimeZone.currentSystemDefault()),
                calories = Random.nextInt(2000)
            )
        }

        val result = calculateMealNutrients(trackedFoods)

        val dinnerCalories = result.mealNutrients.values
            .filter { it.mealType is MealType.Dinner }
            .sumOf { it.calories }
        val expectedCalories = trackedFoods
            .filter { it.mealType is MealType.Dinner }
            .sumOf { it.calories }

        assertThat(dinnerCalories).isEqualTo(expectedCalories)
    }

    @Test
    fun `Calories for snacks properly calculated`() {
        val trackedFoods = (1..30).map {
            TrackedFood(
                name = "name",
                carbs = Random.nextInt(100),
                protein = Random.nextInt(100),
                fat = Random.nextInt(100),
                mealType = MealType.fromString(
                    listOf("breakfast", "lunch", "dinner", "snack").random()
                ),
                imageUrl = null,
                amount = 100,
                date = Clock.System.todayIn(TimeZone.currentSystemDefault()),
                calories = Random.nextInt(2000)
            )
        }

        val result = calculateMealNutrients(trackedFoods)

        val snackCalories = result.mealNutrients.values
            .filter { it.mealType is MealType.Snack }
            .sumOf { it.calories }
        val expectedCalories = trackedFoods
            .filter { it.mealType is MealType.Snack }
            .sumOf { it.calories }

        assertThat(snackCalories).isEqualTo(expectedCalories)
    }
}