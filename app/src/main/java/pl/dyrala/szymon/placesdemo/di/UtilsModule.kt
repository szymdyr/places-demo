package pl.dyrala.szymon.placesdemo.di

import io.reactivex.disposables.CompositeDisposable
import org.koin.dsl.module
import pl.dyrala.szymon.placesdemo.ui.places.adapter.PlacesAdapter

val utilsModule = module {
    factory { PlacesAdapter() }

    factory { CompositeDisposable() }
}