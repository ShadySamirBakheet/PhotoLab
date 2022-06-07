package shady.samir.photolab.data.Database.ModelsDAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import shady.samir.photolab.data.Database.Models.PhotoDB


@Dao
interface PhotoDao {

    @Query("SELECT * FROM PhotoDB")
    fun getAll(): LiveData<List<PhotoDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(post: PhotoDB):Long

    @Query("DELETE FROM PhotoDB")
    suspend fun deleteAll()

}
