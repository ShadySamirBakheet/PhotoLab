package shady.samir.photolab.views.album

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import shady.samir.photolab.R
import shady.samir.photolab.adapters.data.CommentAdapter
import shady.samir.photolab.databinding.ActivityImageDetailsBinding
import shady.samir.photolab.databinding.ActivityPostDetailsBinding

class ImageDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageDetailsBinding

    var  url = ""
    var  tile = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityImageDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.title.text = intent.getStringExtra("title")

        Glide.with(this).load(intent.getStringExtra("url")).placeholder(R.drawable.image2).into(binding.image)
        binding.image.setOnClickListener {
            finish()
        }

    }
}
