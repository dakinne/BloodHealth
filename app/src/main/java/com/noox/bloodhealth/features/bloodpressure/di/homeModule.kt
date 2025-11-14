package com.noox.bloodhealth.features.bloodpressure.di

import com.noox.bloodhealth.core.data.AppDatabase
import com.noox.bloodhealth.features.bloodpressure.data.BloodPressureRepository
import com.noox.bloodhealth.features.bloodpressure.domain.usecase.CreateBloodPressure
import com.noox.bloodhealth.features.bloodpressure.domain.usecase.GetAllBloodPressures
import com.noox.bloodhealth.features.bloodpressure.ui.list.BloodPressureViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeModule = module {

    single { get<AppDatabase>().bloodPressureDao() }
    singleOf(::BloodPressureRepository)
    singleOf(::GetAllBloodPressures)
    singleOf(::CreateBloodPressure)

    viewModelOf(::BloodPressureViewModel)
}