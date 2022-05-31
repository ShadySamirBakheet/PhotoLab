package shady.samir.photolab.data.Database.ModelsDAO

import androidx.lifecycle.LiveData
import androidx.room.*
import shady.samir.photolab.data.Database.Models.PostDB
import shady.samir.photolab.data.Database.Models.User

@Dao
interface PostDao {

    @Query("SELECT * FROM postdb")
    fun getAll(): LiveData<List<PostDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(post: PostDB):Long

    @Query("DELETE FROM PostDB")
    suspend fun deleteAll()


}
