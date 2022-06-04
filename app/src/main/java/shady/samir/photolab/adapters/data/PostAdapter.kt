package shady.samir.photolab.adapters.data

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import shady.samir.photolab.R
import shady.samir.photolab.data.Database.Models.PostDB
import shady.samir.photolab.data.Model.Post
import shady.samir.photolab.utils.Methods
import shady.samir.photolab.views.post.PostDetailsActivity

class PostAdapter (private val context: Context?, private val isFav :Boolean) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

  var data: ArrayList<Post>? = null
 var favs: List<PostDB>? = null
  var size  = 0


  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view: View = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false)
    return ViewHolder(view)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val  item = data?.get(position)
    holder.apply {
      if (item != null) {
        postTitle.text=item.title
        postBody.text=item.body

        if (isFav || getIsFav(item.id)){
          addFav.setImageResource(R.drawable.ic_favorite)
        }else{
          addFav.setImageResource(R.drawable.ic_favorite_out)
        }

        addFav.setOnClickListener {
          Methods.printMSG("on add click")
          onItemLovedListener.let {
            if (it != null) {
              Methods.printMSG("on add click $item")
              it(item)
            }
          }
        }

      }
      itemView.setOnClickListener {
        if (context != null) {
          if (item != null) {
            context.startActivity(Intent(context, PostDetailsActivity::class.java).putExtra("itemId",item.id).putExtra("isFav",isFav || getIsFav(item.id)))
          }
        }
      }
    }

  }

  private fun getIsFav(id: Int): Boolean {
    favs?.forEach {
      if (it.id == id){
        return true
      }
    }
    return false
  }

  private var onItemLovedListener: ((Post) -> Unit)? = null

  fun setOnItemLovedListener(listener: (Post) -> Unit) {
    onItemLovedListener = listener
  }


  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var postTitle: TextView = itemView.findViewById(R.id.postTitle)
    var postBody: TextView = itemView.findViewById(R.id.postBody)
    var addFav: ImageView = itemView.findViewById(R.id.addFav)
  }

  override fun getItemCount(): Int {
    return size
  }

  fun addData(data: ArrayList<Post>?, favs: List<PostDB>?) {
    this.data = data
    this.favs = favs
    size = this.data?.size?:0
    notifyDataSetChanged()
  }

  fun addDataDB(listData: List<PostDB>?) {
    data = ArrayList()
    if (listData != null) {
      listData.forEach {
        data!!.add(Post(it.body,it.id,it.title,it.userId))
      }
    }
    this.data = data
    size = this.data?.size?:0
    notifyDataSetChanged()
  }
}
