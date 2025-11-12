package com.noox.bloodhealth.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.noox.bloodhealth.features.bloodpressure.data.BloodPressureDao
import com.noox.bloodhealth.features.bloodpressure.data.BloodPressureEntity

@Database(
    entities = [
        BloodPressureEntity::class
    ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun bloodPressureDao(): BloodPressureDao
}
