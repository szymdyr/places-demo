package pl.dyrala.szymon.placesdemo.ui.map

import androidx.lifecycle.ViewModel
import io.reactivex.Single
import pl.dyrala.szymon.placesdemo.repositories.models.Place
import pl.dyrala.szymon.placesdemo.repositories.places.PlacesRepository

class MapViewModel(
    private val placesRepository: PlacesRepository
) : ViewModel() {

    fun getPlace(placeId: String): Single<Place> = placesRepository.get(placeId)
}