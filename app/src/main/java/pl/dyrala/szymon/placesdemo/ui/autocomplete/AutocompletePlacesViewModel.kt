package pl.dyrala.szymon.placesdemo.ui.autocomplete

import androidx.lifecycle.ViewModel
import io.reactivex.schedulers.Schedulers
import pl.dyrala.szymon.placesdemo.managers.places.PlacesManager
import pl.dyrala.szymon.placesdemo.repositories.models.Place
import pl.dyrala.szymon.placesdemo.repositories.places.PlacesRepository

class AutocompletePlacesViewModel(
    private val placesRepository: PlacesRepository
) : ViewModel() {

    fun addPlace(place: Place) {
        placesRepository.add(place)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe()
    }
}