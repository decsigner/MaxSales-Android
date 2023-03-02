package com.otokansosoti.maxsales.product.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.otokansosoti.maxsales.databinding.ViewholderProductBinding
import com.otokansosoti.maxsales.fragment.home.HomeModel
import com.otokansosoti.maxsales.fragment.home.viewHolder.product.ProductViewHolder
import com.otokansosoti.maxsales.product.viewholder.ProductItemViewHolder

class ProductListAdapter(private val dataSource: List<HomeModel>, private val lifecycleOwner: LifecycleOwner) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemBinding = ViewholderProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductItemViewHolder(itemBinding, parent.context)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val view = holder as ProductItemViewHolder
        view.bind(dataSource[position])
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }
}