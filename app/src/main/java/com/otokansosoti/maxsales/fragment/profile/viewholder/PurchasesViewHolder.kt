package com.otokansosoti.maxsales.fragment.profile.viewholder

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.otokansosoti.maxsales.databinding.ViewholderPurchaseBinding
import com.otokansosoti.maxsales.fragment.home.HomeModel

class PurchasesViewHolder(private val itemBinding: ViewholderPurchaseBinding): RecyclerView.ViewHolder(itemBinding.root) {
    fun bind(model: HomeModel) {
        val title: TextView = itemBinding.purchaseTitle
        title.text = model.title
    }
}