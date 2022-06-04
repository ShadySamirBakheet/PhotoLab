package shady.samir.photolab.views.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import shady.samir.photolab.adapters.data.PostAdapter
import shady.samir.photolab.databinding.FragmentFavoritesBinding
import shady.samir.photolab.viewmodel.NetworkViewModel
import shady.samir.photolab.viewmodel.PostViewModel

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null

    lateinit var postAdapter: PostAdapter
    lateinit var postViewModel: PostViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        postAdapter = PostAdapter(context,true)
        binding.favList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = postAdapter
        }

        getData()

        return root
    }


    private fun getData() {

        binding.progress.visibility = View.VISIBLE
        postViewModel.getAllFavPost().observe(viewLifecycleOwner) {
            binding.progress.visibility = View.GONE
            if (it!=null&& it.isNotEmpty()) {
                postAdapter.addDataDB(it)
                binding.favList.visibility = View.VISIBLE
                binding.empty.visibility = View.GONE
            } else {
                binding.empty.visibility = View.VISIBLE
                binding.favList.visibility = View.GONE
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
