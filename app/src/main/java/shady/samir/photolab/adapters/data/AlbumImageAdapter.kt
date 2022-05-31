package shady.samir.photolab.adapters.data

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import shady.samir.photolab.R
import shady.samir.photolab.views.album.AlbumDetailsActivity
import shady.samir.photolab.views.album.ImageDetailsActivity
import shady.samir.photolab.views.post.PostDetailsActivity


class AlbumImageAdapter (private val context: Context?) :
    RecyclerView.Adapter<AlbumImageAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_album_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemView.apply {
            setOnClickListener {
                if (context != null) {
                    context.startActivity(Intent(context, ImageDetailsActivity::class.java))
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
//    var itemPrice: TextView = itemView.findViewById(R.id.itemPrice)
//    var image: ImageView = itemView.findViewById(R.id.image)
//    var editItem: ImageView = itemView.findViewById(R.id.editItem)
    }

    override fun getItemCount(): Int {
        return 10
    }
}
