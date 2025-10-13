package com.example.tracker_domain.repository

import com.example.tracker_domain.model.TrackableFood
import com.example.tracker_domain.model.TrackedFood
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.LocalDate

interface TrackerRepository {

    suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int
    ): Result<List<TrackableFood>>

    suspend fun insertTrackedFood(food: TrackedFood)

    suspend fun deleteTrackedFood(food: TrackedFood)

    fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>>
}