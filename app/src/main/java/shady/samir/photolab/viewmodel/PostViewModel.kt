package shady.samir.photolab.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import shady.samir.photolab.data.Apis.Data.RetrofitInstance
import shady.samir.photolab.data.Apis.Data.RetrofitServices
import shady.samir.photolab.data.Database.Database.DataBase
import shady.samir.photolab.data.Database.Models.PostDB
import shady.samir.photolab.data.Model.Comment
import shady.samir.photolab.data.Model.Post
import shady.samir.photolab.data.Repositories.PostRepository
import shady.samir.photolab.utils.ResultApi

class PostViewModel(application: Application) : AndroidViewModel(application) {
    var retService: RetrofitServices
    var postRepository: PostRepository

    init {
        val dataBase = DataBase.getDatabase(application.applicationContext)
        val postDao = dataBase.postDao()
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(RetrofitServices::class.java)
        postRepository = PostRepository(postDao, retService)
    }

    fun addPost(post: PostDB) = viewModelScope.launch {
        postRepository.addPost(post)
    }

    fun deleteAll() = viewModelScope.launch {
        postRepository.deleteAll()
    }

    fun getAllFavPost() = postRepository.getAll()

    fun posts(): LiveData<ResultApi<ArrayList<Post>>> {
        return liveData {
            val response = retService.posts()
            if (response.isSuccessful){
                if (response.isSuccessful &&response.body()!=null && response.body()!!.isNotEmpty()){
                    emit(ResultApi(true, response.body()))
                }else{
                    emit(ResultApi(false, null))
                }
            }else{
                emit(ResultApi(false, null))
            }
        }
    }

    fun post(id: Int): LiveData<ResultApi<Post>> {
        return liveData {
            val response = retService.post(id)
            if (response.isSuccessful){
                if (response.isSuccessful){
                    emit(ResultApi(true, response.body()))
                }else{
                    emit(ResultApi(false, null))
                }
            }else{
                emit(ResultApi(false, null))
            }
        }
    }

    fun postComments(id: Int): LiveData<ResultApi<List<Comment>>> {
        return liveData {
            val response = retService.postComments(id)
            if (response.isSuccessful){
                if (response.isSuccessful){
                    emit(ResultApi(true, response.body()))
                }else{
                    emit(ResultApi(false, null))
                }
            }else{
                emit(ResultApi(false, null))
            }
        }
    }


}
