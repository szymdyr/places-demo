package pl.dyrala.szymon.placesdemo.mappers.places

import pl.dyrala.szymon.placesdemo.database.entities.PlaceEntity
import pl.dyrala.szymon.placesdemo.mappers.Mapper
import pl.dyrala.szymon.placesdemo.repositories.models.Place

class PlaceToEntityPlaceMapper : Mapper<Place, PlaceEntity> {

    override fun map(from: Place): PlaceEntity =
        PlaceEntity(
            from.id,
            from.name,
            from.address,
            from.latitude,
            from.longitude
        )

}