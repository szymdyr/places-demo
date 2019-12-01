package pl.dyrala.szymon.placesdemo

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import pl.dyrala.szymon.placesdemo.di.*

class PlacesApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@PlacesApplication)

            modules(
                listOf(
                    viewModelsModule,
                    managersModule,
                    databaseModule,
                    mappersModule,
                    repositoriesModule,
                    utilsModule
                )
            )
        }
    }
}