package shady.samir.photolab.adapters.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import shady.samir.photolab.R
import shady.samir.photolab.data.Model.Comment

class CommentAdapter(private val context: Context?) :
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.item_comment, parent, false)
        return ViewHolder(view)
    }

    var data: List<Comment>? = null
    var size = 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)
        holder.apply {
            comment.text = item?.body
            name.text = item?.name
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.name)
        var comment: TextView = itemView.findViewById(R.id.comment)
//    var itemPrice: TextView = itemView.findViewById(R.id.itemPrice)
//    var image: ImageView = itemView.findViewById(R.id.image)
//    var editItem: ImageView = itemView.findViewById(R.id.editItem)
    }

    override fun getItemCount(): Int {
        return size
    }

    fun addData(data: List<Comment>?) {
        this.data = data
        size = data?.size ?: 0
        notifyDataSetChanged()
    }
}
