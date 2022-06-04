package shady.samir.photolab.views.album

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import shady.samir.photolab.R
import shady.samir.photolab.adapters.data.AlbumImageAdapter
import shady.samir.photolab.databinding.ActivityAlbumDetailsBinding
import shady.samir.photolab.viewmodel.AlbumViewModel
import shady.samir.photolab.viewmodel.NetworkViewModel

class AlbumDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumDetailsBinding

    lateinit var albumViewModel: AlbumViewModel
    lateinit var networkViewModel: NetworkViewModel
    lateinit var albumImageAdapter: AlbumImageAdapter

    var itemId =0
    var itemName =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.goBack.setOnClickListener {
            finish()
        }


        binding = ActivityAlbumDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        albumViewModel = ViewModelProvider(this).get(AlbumViewModel::class.java)
        networkViewModel = ViewModelProvider(this).get(NetworkViewModel::class.java)

        itemId = intent.getIntExtra("itemId",0)
        itemName = intent.getStringExtra("itemName").toString()

        binding.title.text=itemName

albumImageAdapter=AlbumImageAdapter(this)
        binding.albumsList.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = albumImageAdapter
        }
getData()
    }


    private fun getData() {

        binding.progress.visibility = View.VISIBLE
        networkViewModel.networkState(this).observe(this) {
            if (it) {
                albumViewModel.albumsPhotos(itemId).observe(this) {
                    binding.progress.visibility = View.GONE
                    if (it.isSuccess) {
                        albumImageAdapter.addData(it.data)
                        binding.albumsList.visibility = View.VISIBLE
                        binding.empty.visibility = View.GONE

                    } else {
                        binding.empty.visibility = View.VISIBLE
                        binding.albumsList.visibility = View.GONE
                    }
                }
            }
        }
    }

}
