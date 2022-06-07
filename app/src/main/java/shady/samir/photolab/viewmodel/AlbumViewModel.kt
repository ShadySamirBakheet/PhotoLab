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
import shady.samir.photolab.data.Database.Models.PhotoDB
import shady.samir.photolab.data.Database.Models.PostDB
import shady.samir.photolab.data.Model.Album
import shady.samir.photolab.data.Model.Photo
import shady.samir.photolab.data.Model.Post
import shady.samir.photolab.data.Repositories.AlbumRepository
import shady.samir.photolab.data.Repositories.PostRepository
import shady.samir.photolab.utils.ResultApi

class AlbumViewModel  (application: Application): AndroidViewModel(application) {
    var retService: RetrofitServices
    var albumRepository:AlbumRepository

    init {
        val dataBase = DataBase.getDatabase(application.applicationContext)
        val photoDao = dataBase.photoDao()
        retService = RetrofitInstance
            .getRetrofitInstance()
            .create(RetrofitServices::class.java)
        albumRepository = AlbumRepository( retService,photoDao)
    }

    fun addPhoto(photo: PhotoDB) = viewModelScope.launch {
        albumRepository.addPhoto(photo)
    }

    fun deleteAll() = viewModelScope.launch {
        albumRepository.deleteAll()
    }

    fun getAllFavPhotos() = albumRepository.getAll()


    fun albums(): LiveData<ResultApi<ArrayList<Album>>> {
        return liveData {
            val response = retService.albums()
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

    fun album(id:Int): LiveData<ResultApi<Album>> {
        return liveData {
            val response = retService.album(id)
            if (response.isSuccessful){
                if (response.isSuccessful &&response.body()!=null ){
                    emit(ResultApi(true, response.body()))
                }else{
                    emit(ResultApi(false, null))
                }
            }else{
                emit(ResultApi(false, null))
            }
        }
    }

    fun albumsPhotos(id: Int): LiveData<ResultApi<ArrayList<Photo>>> {
        return liveData {
            val response = retService.albumsPhotos(id )
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

}
