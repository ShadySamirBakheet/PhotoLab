package shady.samir.photolab.views.album

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import shady.samir.photolab.R
import shady.samir.photolab.adapters.data.AlbumImageAdapter
import shady.samir.photolab.databinding.ActivityAlbumDetailsBinding

class AlbumDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAlbumDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.albumsList.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = AlbumImageAdapter(context)
        }

    }
}
