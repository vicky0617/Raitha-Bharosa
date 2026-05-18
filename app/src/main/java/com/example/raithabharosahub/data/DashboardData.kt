package com.example.raithabharosahub.data

import java.util.Locale
import kotlin.random.Random

data class DashboardData(
    val sowingIndex: Int,
    val soilMoisture: Int,
    val temperature: Int,
    val humidity: Int,
    val rainfall: Double,
    val expertAdvice: String,
    val isGoCondition: Boolean // Green for 'Go', Red for 'Wait'
)

object DataGenerator {
    fun generateData(): DashboardData {
        // Condition: Generates random moisture between 10% and 40% as specified
        val moisture = Random.nextInt(10, 41) 
        val temp = Random.nextInt(22, 36)
        val humidity = Random.nextInt(40, 85)
        val rainfall = if (Random.nextBoolean()) 0.0 else Random.nextDouble(0.1, 5.0)
        
        // Brief Logic: evaluation if moisture > 30% display "Soil too wet to sow"
        val (index, advice, isGo) = if (moisture > 30) {
            Triple(
                Random.nextInt(20, 50), 
                "Soil too wet to sow. Delay activity due to high moisture levels.", 
                false
            )
        } else {
            Triple(
                Random.nextInt(75, 96), 
                "Perfect conditions for sowing. Low rain probability and optimal moisture detected.", 
                true
            )
        }

        return DashboardData(
            sowingIndex = index,
            soilMoisture = moisture,
            temperature = temp,
            humidity = humidity,
            rainfall = String.format(Locale.US, "%.1f", rainfall).toDouble(),
            expertAdvice = advice,
            isGoCondition = isGo
        )
    }
}
