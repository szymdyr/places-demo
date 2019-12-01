package pl.dyrala.szymon.placesdemo.managers.places

import android.content.Context
import android.util.Log
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import pl.dyrala.szymon.placesdemo.BuildConfig
import pl.dyrala.szymon.placesdemo.R
import pl.dyrala.szymon.placesdemo.mappers.Mapper
import pl.dyrala.szymon.placesdemo.repositories.models.Place
import pl.dyrala.szymon.placesdemo.ui.autocomplete.AutocompletePlacesFragment
import com.google.android.libraries.places.api.model.Place as GooglePlace


class GooglePlacesManager(
    private val applicationContext: Context,
    private val googlePlaceToPlaceMapper: Mapper<GooglePlace, Place>
) : PlacesManager {

    override var onPlaceSelected: ((Place) -> Unit)? = null

    override val placesFields: ArrayList<GooglePlace.Field> =
        arrayListOf(
            GooglePlace.Field.ID,
            GooglePlace.Field.NAME,
            GooglePlace.Field.LAT_LNG,
            GooglePlace.Field.ADDRESS
        )

    override val placeSelectionListener = object : PlaceSelectionListener {
        override fun onPlaceSelected(googlePlace: GooglePlace) {
            val place = googlePlaceToPlaceMapper.map(googlePlace)

            onPlaceSelected?.invoke(place)
        }

        override fun onError(status: Status) {
            Log.e(
                AutocompletePlacesFragment::javaClass.name,
                status.statusMessage ?: applicationContext.getString(R.string.autocomplete_places_error)
            )
        }
    }

    override fun initialize() {
        if (BuildConfig.PLACES_API_KEY.isEmpty())
            throw IllegalStateException()

        if (!Places.isInitialized()) {
            Places.initialize(applicationContext, BuildConfig.PLACES_API_KEY)
        }
    }
}