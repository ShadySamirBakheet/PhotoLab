package shady.samir.photolab.data.Repositories

import retrofit2.Response
import shady.samir.photolab.data.Apis.Data.RetrofitServices
import shady.samir.photolab.data.Model.Album
import shady.samir.photolab.data.Model.Comment

class AlbumRepository(private val retService: RetrofitServices) {

    suspend fun albums(): Response<ArrayList<Album>> {
        return retService.albums()
    }

    suspend fun album(id: Int): Response<Album> {
        return retService.album(id)
    }

    suspend fun postComments(id: Int): Response<ArrayList<Comment>> {
        return retService.postComments(id)
    }
}
