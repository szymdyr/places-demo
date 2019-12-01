package pl.dyrala.szymon.placesdemo.ui.places

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_places.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import pl.dyrala.szymon.placesdemo.R
import pl.dyrala.szymon.placesdemo.extensions.addTo
import pl.dyrala.szymon.placesdemo.repositories.models.Place
import pl.dyrala.szymon.placesdemo.ui.map.MapFragment.Companion.ARG_PLACE_ID
import pl.dyrala.szymon.placesdemo.ui.places.adapter.PlacesAdapter
import pl.dyrala.szymon.placesdemo.ui.places.swipe.SwipeToDeletePlace

class PlacesFragment : Fragment() {

    private val viewModel: PlacesViewModel by viewModel()

    private val placesAdapter: PlacesAdapter by inject()

    private val compositeDisposable: CompositeDisposable by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_places, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupPlacesRecyclerView()

        startObservingPlaces()
    }

    override fun onDestroyView() {
        super.onDestroyView()

        compositeDisposable.clear()
    }

    private fun setupPlacesRecyclerView() {
        places.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = placesAdapter.apply {
                onClickAction = this@PlacesFragment::showPlaceOnMap
            }
        }

        val swipeCallback = object : SwipeToDeletePlace() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val place = placesAdapter.getPlaceAt(viewHolder.adapterPosition) ?: return

                viewModel.removePlace(place)
            }
        }

        ItemTouchHelper(swipeCallback).attachToRecyclerView(places)
    }

    private fun showPlaceOnMap(place: Place) {
        val bundle = bundleOf(
            ARG_PLACE_ID to place.id
        )

        findNavController().navigate(R.id.navigate_from_places_to_map, bundle)
    }

    private fun startObservingPlaces() =
        viewModel.getPlaces()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { places -> placesAdapter.updateList(places) }
            .subscribe()
            .addTo(compositeDisposable)
}