package pl.dyrala.szymon.placesdemo.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Observable
import io.reactivex.Single
import pl.dyrala.szymon.placesdemo.database.entities.PlaceEntity

@Dao
interface PlacesDao {

    @Query("Select * from places where id =:placeId")
    fun get(placeId: String): Single<PlaceEntity>

    @Query("Select * from places")
    fun getAll() : Observable<List<PlaceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(place: PlaceEntity)

    @Query("Delete from places where id = :placeId")
    fun remove(placeId: String)
}