package pl.dyrala.szymon.placesdemo.repositories.places

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import pl.dyrala.szymon.placesdemo.repositories.models.Place

interface PlacesRepository {

    fun add(place: Place): Completable

    fun remove(placeId: String): Completable

    fun getAll(): Observable<List<Place>>

    fun get(placeId: String): Single<Place>
}