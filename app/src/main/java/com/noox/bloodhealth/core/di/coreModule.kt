package com.noox.bloodhealth.core.di

import com.noox.bloodhealth.core.data.DatabaseFactory
import org.koin.dsl.module

val coreModule = module {
    single { DatabaseFactory.create(get()) }
}