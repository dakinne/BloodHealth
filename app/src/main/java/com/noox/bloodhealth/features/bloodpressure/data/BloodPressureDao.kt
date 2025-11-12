package com.noox.bloodhealth.features.bloodpressure.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BloodPressureDao {

    @Query("SELECT * FROM bloodpressure")
    fun getAll(): Flow<List<BloodPressureEntity>>

    @Query("SELECT * FROM bloodpressure WHERE uid = :uid")
    fun findById(uid: Int): BloodPressureEntity

    @Insert
    fun insert(bloodPressure: BloodPressureEntity)

    @Update
    fun update(bloodPressure: BloodPressureEntity)

    @Delete
    fun delete(bloodPressure: BloodPressureEntity)
}
