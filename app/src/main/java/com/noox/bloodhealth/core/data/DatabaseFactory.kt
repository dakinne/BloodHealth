package com.noox.bloodhealth.core.data

import android.content.Context
import androidx.room.Room
import com.noox.bloodhealth.core.Constant.BLOOD_PRESSURE_DATABASE_NAME
import kotlin.jvm.java

object DatabaseFactory {

    fun create(context: Context): AppDatabase {
        return Room
            .databaseBuilder(
                context = context.applicationContext,
                klass = AppDatabase::class.java,
                name = BLOOD_PRESSURE_DATABASE_NAME
            )
            .build()
    }
}
