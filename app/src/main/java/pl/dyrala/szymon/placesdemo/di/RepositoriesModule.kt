package pl.dyrala.szymon.placesdemo.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import pl.dyrala.szymon.placesdemo.mappers.places.PlaceEntityToPlaceMapper
import pl.dyrala.szymon.placesdemo.mappers.places.PlaceToEntityPlaceMapper
import pl.dyrala.szymon.placesdemo.repositories.places.PlacesRepository
import pl.dyrala.szymon.placesdemo.repositories.places.PlacesRepositoryImpl

val repositoriesModule = module {

    single<PlacesRepository> {
        PlacesRepositoryImpl(
            get(),
            get(named<PlaceEntityToPlaceMapper>()),
            get(named<PlaceToEntityPlaceMapper>())
        )
    }
}