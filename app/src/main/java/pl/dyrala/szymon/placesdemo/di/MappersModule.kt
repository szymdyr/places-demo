package pl.dyrala.szymon.placesdemo.di

import org.koin.core.qualifier.named
import org.koin.dsl.module
import pl.dyrala.szymon.placesdemo.database.entities.PlaceEntity
import pl.dyrala.szymon.placesdemo.mappers.Mapper
import pl.dyrala.szymon.placesdemo.mappers.places.GooglePlaceToPlaceMapper
import pl.dyrala.szymon.placesdemo.mappers.places.PlaceEntityToPlaceMapper
import pl.dyrala.szymon.placesdemo.mappers.places.PlaceToEntityPlaceMapper
import pl.dyrala.szymon.placesdemo.repositories.models.Place
import com.google.android.libraries.places.api.model.Place as GooglePlace

val mappersModule = module {
    single<Mapper<Place, PlaceEntity>>(named<PlaceToEntityPlaceMapper>()) {
        PlaceToEntityPlaceMapper()
    }

    single<Mapper<PlaceEntity, Place>>(named<PlaceEntityToPlaceMapper>()) {
        PlaceEntityToPlaceMapper()
    }

    single<Mapper<GooglePlace, Place>>(named<GooglePlaceToPlaceMapper>()) {
        GooglePlaceToPlaceMapper()
    }
}