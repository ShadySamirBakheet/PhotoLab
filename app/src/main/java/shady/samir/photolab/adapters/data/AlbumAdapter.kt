package shady.samir.photolab.adapters.data

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import shady.samir.photolab.R
import shady.samir.photolab.data.Model.Album
import shady.samir.photolab.views.album.AlbumDetailsActivity

class AlbumAdapter(private val context: Context?) :
    RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

    var data: ArrayList<Album>? = null
    var size = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_album, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = data?.get(position)
        holder.apply {
            itemName.text = item?.title
            itemView.setOnClickListener {
                context?.startActivity(
                    Intent(
                        context,
                        AlbumDetailsActivity::class.java
                    ).putExtra("itemId", item?.id)
                    .putExtra("itemName", item?.title)
                )
            }
        }

    }

    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemName: TextView = itemView.findViewById(R.id.itemName)
//    var itemDesc: TextView = itemView.findViewById(R.id.itemDesc)
//    var itemPrice: TextView = itemView.findViewById(R.id.itemPrice)
//    var image: ImageView = itemView.findViewById(R.id.image)
//    var editItem: ImageView = itemView.findViewById(R.id.editItem)
    }

    override fun getItemCount(): Int {
        return size
    }

    fun addData(data: ArrayList<Album>?) {
        this.data = data
        size = data?.size ?: 0
        notifyDataSetChanged()
    }
}
