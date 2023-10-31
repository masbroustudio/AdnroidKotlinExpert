package com.yudhae.kokasappstarter.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yudhae.kokasappstarter.R
import com.yudhae.kokasappstarter.core.domain.model.Kokas
import com.yudhae.kokasappstarter.databinding.ItemListTourismBinding
import java.util.ArrayList

class KokasAdapter : RecyclerView.Adapter<KokasAdapter.ListViewHolder>() {

    private var listData = ArrayList<Kokas>()
    var onItemClick: ((Kokas) -> Unit)? = null

    fun setData(newListData: List<Kokas>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_tourism, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListTourismBinding.bind(itemView)
        fun bind(data: Kokas) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(ivItemImage)
                tvItemTitle.text = data.title
                tvItemSubtitle.text = data.description
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}