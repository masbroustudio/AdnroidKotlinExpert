package com.dicoding.tourismapp.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.yudhae.kokasappstarter.core.ui.KokasAdapter
import com.yudhae.kokasappstarter.core.ui.ViewModelFactory
import com.yudhae.kokasappstarter.databinding.FragmentFavoriteBinding
import com.yudhae.kokasappstarter.detail.DetailKokasActivity
import com.yudhae.kokasappstarter.favorite.FavoriteViewModel

class FavoriteFragment : Fragment() {

    private lateinit var favoriteViewModel: FavoriteViewModel

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val kokasAdapter = KokasAdapter()
            kokasAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailKokasActivity::class.java)
                intent.putExtra(DetailKokasActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            val factory = ViewModelFactory.getInstance(requireActivity())
            favoriteViewModel = ViewModelProvider(this, factory)[FavoriteViewModel::class.java]

            favoriteViewModel.favoriteTourism.observe(viewLifecycleOwner, { dataTourism ->
                kokasAdapter.setData(dataTourism)
                binding.viewEmpty.root.visibility = if (dataTourism.isNotEmpty()) View.GONE else View.VISIBLE
            })

            with(binding.rvTourism) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = kokasAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
