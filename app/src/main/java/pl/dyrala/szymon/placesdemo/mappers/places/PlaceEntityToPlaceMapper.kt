package pl.dyrala.szymon.placesdemo.mappers.places

import pl.dyrala.szymon.placesdemo.database.entities.PlaceEntity
import pl.dyrala.szymon.placesdemo.mappers.Mapper
import pl.dyrala.szymon.placesdemo.repositories.models.Place

class PlaceEntityToPlaceMapper : Mapper<PlaceEntity, Place> {

    override fun map(from: PlaceEntity): Place =
        Place(
            from.placeId,
            from.name,
            from.address,
            from.latitude,
            from.longitude
        )
}