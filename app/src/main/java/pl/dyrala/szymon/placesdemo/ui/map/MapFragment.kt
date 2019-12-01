package pl.dyrala.szymon.placesdemo.ui.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import pl.dyrala.szymon.placesdemo.R
import pl.dyrala.szymon.placesdemo.extensions.addTo
import pl.dyrala.szymon.placesdemo.repositories.models.Place


class MapFragment : Fragment(), OnMapReadyCallback {

    private val viewModel: MapViewModel by viewModel()

    private val compositeDisposable: CompositeDisposable by inject()

    private var map: GoogleMap? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_map, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getMap()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        compositeDisposable.clear()
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap

        getPlace()
    }

    private fun getMap() {
        (childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment)?.getMapAsync(this)
    }

    private fun getPlace() {
        val placeId = arguments?.getString(ARG_PLACE_ID)

        if (placeId.isNullOrEmpty()) {
            Log.e(MapFragment::javaClass.name, "Place id is missing - cannot show place")
            return
        }

        viewModel.getPlace(placeId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess(this::showPlaceOnMap)
            .doOnError { Log.e(MapFragment::javaClass.name, "Cannot find place with id = $placeId") }
            .subscribe()
            .addTo(compositeDisposable)
    }

    private fun showPlaceOnMap(place: Place) {
        val position = LatLng(place.latitude, place.longitude)
        val marker = MarkerOptions()
            .position(position)
            .title(place.name)

        map?.addMarker(marker)
        map?.moveCamera(CameraUpdateFactory.newLatLngZoom(position, ZOOM_LEVEL_STREETS))
    }

    companion object {
        const val ARG_PLACE_ID = "ARG_PLACE_ID"

        private const val ZOOM_LEVEL_STREETS = 10.0f
    }
}