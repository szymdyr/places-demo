package pl.dyrala.szymon.placesdemo.mappers.places

import com.google.android.libraries.places.api.model.Place as GooglePlace
import pl.dyrala.szymon.placesdemo.mappers.Mapper
import pl.dyrala.szymon.placesdemo.repositories.models.Place

class GooglePlaceToPlaceMapper : Mapper<GooglePlace, Place> {

    override fun map(from: GooglePlace): Place =
        Place(
            from.id ?: "",
            from.name ?: "",
            from.address ?: "",
            from.latLng?.latitude ?: 0.0,
            from.latLng?.longitude ?: 0.0
        )
}