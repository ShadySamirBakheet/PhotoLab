package shady.samir.photolab.data.datasources

import android.content.Context
import android.content.SharedPreferences
import shady.samir.photolab.data.models.User

class SharedStorage {
    companion object {

        fun saveLoginData(context: Context, user: User) {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences("login", Context.MODE_PRIVATE)
            val edit = sharedPreferences.edit()
            edit.putString("login_id", user.id)
            edit.putString("userName", user.userName)
            edit.putString("fullName", user.fullName)
            edit.putString("user_img", user.userImg)
            edit.putBoolean("isAdmin", user.isAdmin)
            edit.apply()
        }


        fun getUser(context: Context): User {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences("login", Context.MODE_PRIVATE)
            return User(
                id = sharedPreferences.getString("login_id", ""),
                userName = sharedPreferences.getString("userName", ""),
                fullName = sharedPreferences.getString("fullName", ""),
                userImg = sharedPreferences.getString("user_img",""),
                isAdmin = sharedPreferences.getBoolean("isAdmin",false)
            )
        }


        fun getUserName(context: Context): String? {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences("login", Context.MODE_PRIVATE)
            return sharedPreferences.getString("userName", "")
        }

        fun getFullName(context: Context): String {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences("login", Context.MODE_PRIVATE)

            return sharedPreferences.getString("fullName", "").toString()
        }

        fun getLoginImagesData(context: Context): String {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences("login", Context.MODE_PRIVATE)
            return sharedPreferences.getString("user_img", "").toString()
        }

        fun getLoginIsAdminData(context: Context): Boolean {
            val sharedPreferences: SharedPreferences =
                context.getSharedPreferences("login", Context.MODE_PRIVATE)
            return sharedPreferences.getBoolean("isAdmin", false)
        }


    }
}
