package shady.samir.photolab.views.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import shady.samir.photolab.adapters.data.AlbumAdapter
import shady.samir.photolab.adapters.data.PostAdapter
import shady.samir.photolab.databinding.FragmentAlbumBinding
import shady.samir.photolab.viewmodel.AlbumViewModel
import shady.samir.photolab.viewmodel.NetworkViewModel
import shady.samir.photolab.viewmodel.PostViewModel

class AlbumFragment : Fragment() {

    private var _binding: FragmentAlbumBinding? = null

    lateinit var albumAdapter: AlbumAdapter
    lateinit var albumViewModel: AlbumViewModel
    lateinit var networkViewModel: NetworkViewModel
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        albumViewModel = ViewModelProvider(this).get(AlbumViewModel::class.java)
        networkViewModel = ViewModelProvider(this).get(NetworkViewModel::class.java)


        _binding = FragmentAlbumBinding.inflate(inflater, container, false)
        val root: View = binding.root

        albumAdapter=AlbumAdapter(context)
        binding.albumsList.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = albumAdapter
        }

        getData()

        return root
    }


    private fun getData() {

        binding.progress.visibility = View.VISIBLE
        networkViewModel.networkState(context).observe(viewLifecycleOwner) {
            if (it) {
                albumViewModel.albums().observe(viewLifecycleOwner) {
                    binding.progress.visibility = View.GONE
                    if (it.isSuccess) {
                        albumAdapter.addData(it.data)
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
