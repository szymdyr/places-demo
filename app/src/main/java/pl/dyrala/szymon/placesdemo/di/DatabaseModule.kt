package pl.dyrala.szymon.placesdemo.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import pl.dyrala.szymon.placesdemo.database.PlacesDatabase

val databaseModule = module {
    single(createdAtStart = true) { PlacesDatabase.getInstance(androidContext()) }
}