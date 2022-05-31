package shady.samir.photolab.data.Apis.Data


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import shady.samir.photolab.data.Model.Album
import shady.samir.photolab.data.Model.Comment
import shady.samir.photolab.data.Model.Photo
import shady.samir.photolab.data.Model.Post

interface RetrofitServices {

    @GET("posts")
    suspend fun posts(): Response<List<Post>>

    @GET("posts/{id}")
    suspend fun post(@Path("id") id: Int): Response<Post>

    @GET("posts/{id}/comments")
    suspend fun postComments(@Path("id") id: Int): Response<List<Comment>>


    @GET("albums")
    suspend fun albums(): Response<List<Album>>

    @GET("albums/{id}")
    suspend fun album(@Path("id") id: Int): Response<Album>

    @GET("albums/{id}/photos")
    suspend fun albumsPhotos(@Path("id") id: Int): Response<List<Photo>>

}
