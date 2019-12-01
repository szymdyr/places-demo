package pl.dyrala.szymon.placesdemo.repositories.places

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import pl.dyrala.szymon.placesdemo.database.PlacesDatabase
import pl.dyrala.szymon.placesdemo.database.entities.PlaceEntity
import pl.dyrala.szymon.placesdemo.mappers.Mapper
import pl.dyrala.szymon.placesdemo.repositories.models.Place

class PlacesRepositoryImpl(
    private val database: PlacesDatabase,
    private val placeEntityToPlaceMapper: Mapper<PlaceEntity, Place>,
    private val placeToEntityPlaceMapper: Mapper<Place, PlaceEntity>
) : PlacesRepository {

    override fun add(place: Place): Completable {
        val placeEntity = placeToEntityPlaceMapper.map(place)

        return Completable.fromAction {
            database.placesDao().insert(placeEntity)
        }
    }

    override fun remove(placeId: String): Completable =
        Completable.fromAction {
            database.placesDao().remove(placeId)
        }

    override fun getAll(): Observable<List<Place>> =
        database.placesDao()
            .getAll()
            .map { it.map(placeEntityToPlaceMapper::map) }

    override fun get(placeId: String): Single<Place> =
        database.placesDao()
            .get(placeId)
            .map(placeEntityToPlaceMapper::map)
}