package shady.samir.photolab.adapters.data

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import shady.samir.photolab.R
import shady.samir.photolab.data.Model.Photo
import shady.samir.photolab.views.album.ImageDetailsActivity


class AlbumImageAdapter(private val context: Context?) :
    RecyclerView.Adapter<AlbumImageAdapter.ViewHolder>() {

    var data: ArrayList<Photo>? = null
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
            itemView.setOnClickListener {
                if (context != null) {
                    context.startActivity(Intent(context, ImageDetailsActivity::class.java).putExtra("url",item?.url).putExtra("title",item?.title),)
                }
            }
        }

    }

    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //    var itemName: TextView = itemView.findViewById(R.id.itemName)
//    var itemDesc: TextView = itemView.findViewById(R.id.itemDesc)
        var title: TextView = itemView.findViewById(R.id.title)
        var image: ImageView = itemView.findViewById(R.id.image)
//    var editItem: ImageView = itemView.findViewById(R.id.editItem)
    }

    override fun getItemCount(): Int {
        return size
    }


    fun addData(data: ArrayList<Photo>?) {
        this.data = data
        size = data?.size ?: 0
        notifyDataSetChanged()
    }
}
