package shady.samir.photolab.data.Repositories

import shady.samir.photolab.data.Database.Models.User
import shady.samir.photolab.data.Database.ModelsDAO.UserDao


class UserRepository(var userDao: UserDao) {

    var getUsers = userDao.getAll()

    suspend fun addUser(user: User){
        userDao.insert(user)
    }

    suspend fun deleteAllUser(){
        userDao.deleteAll()
    }

    fun getUser(id: Int) = userDao.getuser(id)

}
