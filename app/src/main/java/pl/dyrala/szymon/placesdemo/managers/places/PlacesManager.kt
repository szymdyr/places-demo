package pl.dyrala.szymon.placesdemo.managers.places

import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import pl.dyrala.szymon.placesdemo.repositories.models.Place
import com.google.android.libraries.places.api.model.Place as GooglePlace


interface PlacesManager {

    val placesFields: ArrayList<GooglePlace.Field>

    val placeSelectionListener: PlaceSelectionListener

    var onPlaceSelected: ((Place) -> Unit)?

    /**
     * Initialize Places SDK if needed. If it has been already initialized - nothing happen
     */
    fun initialize()
}