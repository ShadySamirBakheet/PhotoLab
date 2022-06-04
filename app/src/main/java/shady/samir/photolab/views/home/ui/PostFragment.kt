package shady.samir.photolab.views.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import shady.samir.photolab.adapters.data.PostAdapter
import shady.samir.photolab.data.Database.Models.PostDB
import shady.samir.photolab.databinding.FragmentPostBinding
import shady.samir.photolab.utils.Methods
import shady.samir.photolab.viewmodel.NetworkViewModel
import shady.samir.photolab.viewmodel.PostViewModel

class PostFragment : Fragment() {

    private var _binding: FragmentPostBinding? = null

    lateinit var postAdapter: PostAdapter
    lateinit var postViewModel: PostViewModel
    lateinit var networkViewModel: NetworkViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        networkViewModel = ViewModelProvider(this).get(NetworkViewModel::class.java)

        _binding = FragmentPostBinding.inflate(inflater, container, false)
        val root: View = binding.root

        postAdapter = PostAdapter(context,false)
        binding.postsList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = postAdapter
        }

        postAdapter.setOnItemLovedListener {
            Methods.printMSG("on add click out")
            postViewModel.addPost(PostDB(it.id,it.userId,it.title,it.body))
        }

        getData()

        return root
    }

    private fun getData() {

        binding.progress.visibility = View.VISIBLE
        networkViewModel.networkState(context).observe(viewLifecycleOwner) {
            if (it) {
                postViewModel.posts().observe(viewLifecycleOwner) {
                    binding.progress.visibility = View.GONE
                    if (it.isSuccess) {
                        postViewModel.getAllFavPost().observe(viewLifecycleOwner){db->
                            postAdapter.addData(it.data,db)
                            binding.postsList.visibility = View.VISIBLE
                            binding.empty.visibility = View.GONE
                        }

                    } else {
                        binding.empty.visibility = View.VISIBLE
                        binding.postsList.visibility = View.GONE
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
