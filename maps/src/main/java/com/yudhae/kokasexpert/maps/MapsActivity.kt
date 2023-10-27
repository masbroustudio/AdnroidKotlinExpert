package com.yudhae.kokasexpert.maps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.yudhae.kokasexpert.core.data.Resource
import com.yudhae.kokasexpert.maps.databinding.ActivityMapsBinding
import com.yudhae.kokasexpert.maps.di.mapsModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class MapsActivity : AppCompatActivity() {
    private val mapsViewModel: MapsViewModel by viewModel()
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_maps)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(mapsModule)

        supportActionBar?.title = "Kokas Map"
        getKokasData()
    }
    private fun getKokasData() {
        mapsViewModel.kokas.observe(this, { kokas ->
            if (kokas != null) {
                when (kokas) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvMaps.text = "This is map of ${kokas.data?.get(0)?.name}"
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvError.visibility = View.VISIBLE
                        binding.tvError.text = kokas.message
                    }
                }
            }
        })
    }
}