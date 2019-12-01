package pl.dyrala.szymon.placesdemo.di

import org.koin.android.ext.koin.androidApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module
import pl.dyrala.szymon.placesdemo.managers.places.GooglePlacesManager
import pl.dyrala.szymon.placesdemo.managers.places.PlacesManager
import pl.dyrala.szymon.placesdemo.mappers.places.GooglePlaceToPlaceMapper

val managersModule = module {

    single<PlacesManager> {
        GooglePlacesManager(
            androidApplication(),
            get(named<GooglePlaceToPlaceMapper>())
        )
    }
}