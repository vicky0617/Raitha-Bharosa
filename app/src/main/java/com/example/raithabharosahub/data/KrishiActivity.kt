package com.example.raithabharosahub.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "krishi_activities")
data class KrishiActivity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val dateString: String, // Format: "YYYY-MM-DD" or just day number "15", "16"
    val title: String,
    val time: String,
    val category: String // "High Priority", "Fertilizer", "Monitoring", "Water"
)

@Dao
interface ActivityDao {
    @Query("SELECT * FROM krishi_activities WHERE dateString = :date")
    fun getActivitiesForDate(date: String): Flow<List<KrishiActivity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertActivity(activity: KrishiActivity)
}
