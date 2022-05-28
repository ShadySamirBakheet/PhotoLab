package shady.samir.photolab.data.models


data class User (
    val id: String? = null,
    var userName:String? = null,
    var fullName:String? = null,
    var userImg: String? = null,
    var userEmail:String? = null,
    var userPhone:String? = null,
    var isAdmin:Boolean= false,
)
