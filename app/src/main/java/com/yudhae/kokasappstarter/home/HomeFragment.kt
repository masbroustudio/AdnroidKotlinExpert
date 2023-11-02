package com.yudhae.kokasappstarter.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.yudhae.kokasappstarter.R
import com.yudhae.kokasappstarter.core.data.Resource
import com.yudhae.kokasappstarter.core.ui.KokasAdapter
import com.yudhae.kokasappstarter.databinding.FragmentHomeBinding
import com.yudhae.kokasappstarter.detail.DetailKokasActivity
import org.koin.android.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
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

            homeViewModel.kokas.observe(viewLifecycleOwner, { kokas ->
                if (kokas != null) {
                    when (kokas) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            kokasAdapter.setData(kokas.data)
                        }

                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                kokas.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
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
