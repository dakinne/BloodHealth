package com.noox.bloodhealth.features.bloodpressure.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BloodPressureDao {

    @Query("SELECT * FROM bloodpressure ORDER BY uid DESC")
    fun getAll(): Flow<List<BloodPressureEntity>>

    @Query("SELECT * FROM bloodpressure WHERE uid = :uid")
    suspend fun findById(uid: Int): BloodPressureEntity

    @Insert
    suspend fun insert(bloodPressure: BloodPressureEntity)

    @Update
    suspend fun update(bloodPressure: BloodPressureEntity)

    @Delete
    suspend fun delete(bloodPressure: BloodPressureEntity)
}
