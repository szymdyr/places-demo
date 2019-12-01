package pl.dyrala.szymon.placesdemo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pl.dyrala.szymon.placesdemo.database.daos.PlacesDao
import pl.dyrala.szymon.placesdemo.database.entities.PlaceEntity

@Database(
    entities = [PlaceEntity::class],
    version = 1
)
abstract class PlacesDatabase : RoomDatabase() {

    abstract fun placesDao(): PlacesDao

    companion object {
        private const val DATABASE_NAME = "places.db"

        private var INSTANCE: PlacesDatabase? = null

        fun getInstance(context: Context): PlacesDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: create(context).also { INSTANCE = it }
            }

        private fun create(context: Context): PlacesDatabase =
            Room.databaseBuilder(
                context,
                PlacesDatabase::class.java,
                DATABASE_NAME
            ).build()
    }
}