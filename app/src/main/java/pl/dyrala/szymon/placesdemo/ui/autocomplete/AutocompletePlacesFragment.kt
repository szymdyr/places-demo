package pl.dyrala.szymon.placesdemo.ui.autocomplete

import android.os.Bundle
import android.util.Log
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import pl.dyrala.szymon.placesdemo.managers.places.PlacesManager
import com.google.android.libraries.places.api.model.Place as GooglePlace

class AutocompletePlacesFragment : AutocompleteSupportFragment() {

    private val viewModel: AutocompletePlacesViewModel by viewModel()

    private val placesManager: PlacesManager by inject()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupPlacesManager()

        setupPlacesAutocomplete()
    }

    private fun setupPlacesManager() {
        try {
            placesManager.initialize()
        } catch (ex: IllegalStateException) {
            Log.e(AutocompletePlacesFragment::javaClass.name, "Cannot find PLACES_API_KEY")
            return
        }

        placesManager.onPlaceSelected = { place ->
            viewModel.addPlace(place)
        }
    }

    private fun setupPlacesAutocomplete() {
        setPlaceFields(placesManager.placesFields)

        setOnPlaceSelectedListener(placesManager.placeSelectionListener)
    }
}