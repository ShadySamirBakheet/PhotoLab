package shady.samir.photolab.data.Database.ModelsDAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import shady.samir.photolab.data.Database.Models.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAll(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User):Long

    @Query("DELETE FROM user")
    suspend fun deleteAll()

    @Query("SELECT * FROM user where userid = :id")
    fun getuser(id: Int): LiveData<User>

}
