package shady.samir.photolab.views.album

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import shady.samir.photolab.adapters.data.CommentAdapter
import shady.samir.photolab.databinding.ActivityImageDetailsBinding
import shady.samir.photolab.databinding.ActivityPostDetailsBinding

class ImageDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImageDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityImageDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.image.setOnClickListener {
            finish()
        }

    }
}
