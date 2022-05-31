package shady.samir.photolab.views.post

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import shady.samir.photolab.R
import shady.samir.photolab.adapters.data.CommentAdapter
import shady.samir.photolab.adapters.data.PostAdapter
import shady.samir.photolab.databinding.ActivityMainBinding
import shady.samir.photolab.databinding.ActivityPostDetailsBinding

class PostDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPostDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.comments.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = CommentAdapter(context)
        }

    }
}
