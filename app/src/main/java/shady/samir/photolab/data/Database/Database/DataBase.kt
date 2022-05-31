package shady.samir.photolab.data.Database.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import shady.samir.photolab.data.Database.Models.PostDB
import shady.samir.photolab.data.Database.Models.User
import shady.samir.photolab.data.Database.ModelsDAO.PostDao
import shady.samir.photolab.data.Database.ModelsDAO.UserDao

@Database(entities = [User::class,PostDB::class], version = 1)

abstract class DataBase: RoomDatabase() {


    abstract fun userDao(): UserDao
    abstract fun postDao(): PostDao

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null

        fun getDatabase(
                context: Context
        ): DataBase {
            if (INSTANCE == null){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        DataBase::class.java,
                        "aplexshipping"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
            }
            return INSTANCE!!
        }

    }

}
