package pl.dyrala.szymon.placesdemo.ui.places

import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pl.dyrala.szymon.placesdemo.managers.places.PlacesManager
import pl.dyrala.szymon.placesdemo.repositories.models.Place
import pl.dyrala.szymon.placesdemo.repositories.places.PlacesRepository

class PlacesViewModel(
    private val repository: PlacesRepository
) : ViewModel() {

    fun getPlaces() = repository.getAll()

    fun removePlace(place: Place) {
        repository.remove(place.id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe()
    }
}