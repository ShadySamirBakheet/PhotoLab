package shady.samir.photolab.data.Repositories

import retrofit2.Response
import shady.samir.photolab.data.Apis.Data.RetrofitInstance
import shady.samir.photolab.data.Apis.Data.RetrofitServices
import shady.samir.photolab.data.Database.Models.PostDB
import shady.samir.photolab.data.Database.ModelsDAO.PostDao
import shady.samir.photolab.data.Model.Comment
import shady.samir.photolab.data.Model.Post

class PostRepository(private val postDao: PostDao, private  val retService: RetrofitServices) {


    suspend fun posts(): Response<List<Post>> {

        return  retService.posts()
    }

    suspend fun post(id:Int): Response<Post> {

        return  retService.post(id)
    }

    suspend fun postComments(id:Int): Response<List<Comment>> {

        return  retService.postComments(id)
    }


    suspend fun addPost(post: PostDB){
        postDao.insert(post)
    }

    suspend fun deleteAll(){
        postDao.deleteAll()
    }

    fun getAll() = postDao.getAll()
}
