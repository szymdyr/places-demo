package pl.dyrala.szymon.placesdemo.di

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import pl.dyrala.szymon.placesdemo.ui.autocomplete.AutocompletePlacesViewModel
import pl.dyrala.szymon.placesdemo.ui.map.MapViewModel
import pl.dyrala.szymon.placesdemo.ui.places.PlacesViewModel

val viewModelsModule = module {
    viewModel { PlacesViewModel(get()) }
    viewModel { AutocompletePlacesViewModel(get()) }
    viewModel { MapViewModel(get()) }
}