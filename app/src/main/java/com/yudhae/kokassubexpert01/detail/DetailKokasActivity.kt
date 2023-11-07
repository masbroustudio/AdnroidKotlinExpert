package com.yudhae.kokassubexpert01.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.kokassubexpert01.core.domain.model.Kokas
import com.yudhae.kokassubexpert01.R
import com.yudhae.kokassubexpert01.databinding.ActivityDetailKokasBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailKokasActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailKokasViewModel: DetailKokasViewModel by viewModels()

    private lateinit var binding: ActivityDetailKokasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailKokasBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val detailKokas = intent.getParcelableExtra<Kokas>(EXTRA_DATA)
        showDetailKokas(detailKokas)
    }

    private fun showDetailKokas(detailKokas: Kokas?) {
        detailKokas?.let {
            supportActionBar?.title = detailKokas.title
            binding.content.tvDetailDescription.text = detailKokas.description
            Glide.with(this@DetailKokasActivity)
                .load(detailKokas.image)
                .into(binding.ivDetailImage)

            var statusFavorite = detailKokas.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailKokasViewModel.setFavoriteKokas(detailKokas, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_white
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_not_favorite_white
                )
            )
        }
    }
}
