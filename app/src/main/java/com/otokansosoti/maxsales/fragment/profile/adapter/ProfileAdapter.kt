package com.otokansosoti.maxsales.fragment.profile.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.otokansosoti.maxsales.databinding.ViewholderPurchaseBinding
import com.otokansosoti.maxsales.fragment.home.HomeModel
import com.otokansosoti.maxsales.fragment.profile.viewholder.PurchasesViewHolder

class ProfileAdapter(private val dataSource: List<HomeModel>, private val lifecycleOwner: LifecycleOwner) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemBinding = ViewholderPurchaseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PurchasesViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val view = holder as PurchasesViewHolder
        view.bind(dataSource[position])
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }
}