package shady.samir.photolab.data.Database.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
class PostDB(
    @ColumnInfo(name = "userId")  val userId: Int,
    @PrimaryKey
    @ColumnInfo(name = "id")  val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "body")  val body: String,

)
