package shady.samir.photolab.data.Repositories

import retrofit2.Response
import shady.samir.photolab.data.Apis.Data.RetrofitServices
import shady.samir.photolab.data.Database.Models.PhotoDB
import shady.samir.photolab.data.Database.Models.PostDB
import shady.samir.photolab.data.Database.ModelsDAO.PhotoDao
import shady.samir.photolab.data.Model.Album
import shady.samir.photolab.data.Model.Comment

class AlbumRepository(private val retService: RetrofitServices,private val photoDao: PhotoDao) {

    suspend fun albums(): Response<ArrayList<Album>> {
        return retService.albums()
    }

    suspend fun album(id: Int): Response<Album> {
        return retService.album(id)
    }

    suspend fun postComments(id: Int): Response<ArrayList<Comment>> {
        return retService.postComments(id)
    }


    suspend fun addPhoto(photo: PhotoDB){
        photoDao.insert(photo)
    }

    suspend fun deleteAll(){
        photoDao.deleteAll()
    }

    fun getAll() = photoDao.getAll()

}
