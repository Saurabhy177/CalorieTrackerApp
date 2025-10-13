package com.example.tracker_presentation.tracker_overview.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.core.R
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn

@Composable
fun ParseDateText(date: LocalDate): String {
    val today = Clock.System.todayIn(TimeZone.currentSystemDefault())
    return when(date) {
        today -> stringResource(R.string.today)
        today.minus(1, DateTimeUnit.DAY) -> stringResource(R.string.yesterday)
        today.plus(1, DateTimeUnit.DAY) -> stringResource(R.string.tomorrow)
        else -> {
            // Get the month name and capitalize it (e.g., "OCTOBER" -> "October")
            val month = date.month.name
                .lowercase()
                .replaceFirstChar { it.titlecase() }

            // Format to "dd MonthName" (e.g., "23 October")
            "${date.dayOfMonth} $month"
        }
    }
}