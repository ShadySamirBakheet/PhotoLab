package shady.samir.photolab.adapters.data

import android.content.Context
import android.content.Intent
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import shady.samir.photolab.R
import shady.samir.photolab.data.Database.Models.PhotoDB
import shady.samir.photolab.data.Database.Models.PostDB
import shady.samir.photolab.data.Model.Photo
import shady.samir.photolab.data.Model.Post
import shady.samir.photolab.utils.Methods
import shady.samir.photolab.views.album.ImageDetailsActivity


class AlbumImageAdapter(private val context: Context?, private val isFav :Boolean) :
    RecyclerView.Adapter<AlbumImageAdapter.ViewHolder>() {

    var data: ArrayList<Photo>? = null
    var favs: List<PhotoDB>? = null

    var size = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.item_album_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)
        holder.apply {
            title.text = item?.title
            if (context != null) {
                Glide.with(context).load(item?.url?:item?.thumbnailUrl).placeholder(R.drawable.image2).into(image)
            }

            if (isFav || getIsFav(item!!.id)){
                addFav.setImageResource(R.drawable.ic_favorite)
            }else{
                addFav.setImageResource(R.drawable.ic_favorite_out)
            }


            addFav.setOnClickListener {
                Methods.printMSG("on add click")
                onItemLovedListener.let {
                    if (it != null) {
                        Methods.printMSG("on add click $item")
                        if (item != null) {
                            it(item)
                        }
                    }
                }
            }


            itemView.setOnClickListener {
                if (context != null) {
                    context.startActivity(Intent(context, ImageDetailsActivity::class.java).putExtra("url",item?.url).putExtra("title",item?.title),)
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


    private var onItemLovedListener: ((Photo) -> Unit)? = null

    fun setOnItemLovedListener(listener: (Photo) -> Unit) {
        onItemLovedListener = listener
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.title)
        var image: ImageView = itemView.findViewById(R.id.image)
        var addFav: ImageView = itemView.findViewById(R.id.addFav)
    }

    override fun getItemCount(): Int {
        return size
    }


    fun addData(data: ArrayList<Photo>?, loacl: List<PhotoDB>) {
        this.data = data
        favs= loacl
        size = data?.size ?: 0
        notifyDataSetChanged()
    }

    fun addDataDB(listData: List<PhotoDB>) {
        data = ArrayList()
        if (listData != null) {
            listData.forEach {
                data!!.add(Photo(it.albumId,it.id,it.thumbnailUrl,it.title,it.url))
            }
        }
        this.data = data
        size = this.data?.size?:0
        notifyDataSetChanged()
    }
}
