package shady.samir.photolab.views.post

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import shady.samir.photolab.R
import shady.samir.photolab.adapters.data.CommentAdapter
import shady.samir.photolab.databinding.ActivityPostDetailsBinding
import shady.samir.photolab.viewmodel.NetworkViewModel
import shady.samir.photolab.viewmodel.PostViewModel

class PostDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostDetailsBinding

    lateinit var commentAdapter: CommentAdapter
    lateinit var postViewModel: PostViewModel
    lateinit var networkViewModel: NetworkViewModel
    var itemId = 0
    var isFav = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        networkViewModel = ViewModelProvider(this).get(NetworkViewModel::class.java)

        binding = ActivityPostDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goBack.setOnClickListener {
            finish()
        }


        itemId = intent.getIntExtra("itemId", 0)
        isFav = intent.getBooleanExtra("isFav", false)

        if (isFav) {
            binding.post.addFav.setImageResource(R.drawable.ic_favorite)
        } else {
            binding.post.addFav.setImageResource(R.drawable.ic_favorite_out)
        }

        commentAdapter = CommentAdapter(this)

        binding.comments.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = commentAdapter
        }

        getData()

    }

    private fun getData() {

        binding.progress.visibility = View.VISIBLE
        networkViewModel.networkState(this).observe(this) {
            if (it) {
                postViewModel.post(itemId).observe(this) {item->
                    binding.progress.visibility = View.GONE
                    if (item.isSuccess) {
                        binding.post.postTitle.text  = item.data?.title
                        binding.post.postBody.text  = item.data?.body
                        postViewModel.postComments(item.data?.id?:itemId).observe(this){
                            if (it.isSuccess){
                                commentAdapter.addData(it.data)
                            }
                        }
                    }
                }
            }
        }
    }
}
