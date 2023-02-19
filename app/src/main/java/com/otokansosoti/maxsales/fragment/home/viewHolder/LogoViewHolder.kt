package com.otokansosoti.maxsales.fragment.home.viewHolder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.otokansosoti.maxsales.R
import com.otokansosoti.maxsales.databinding.ViewholderLogoBinding

class LogoViewHolder(private val itemBinding: ViewholderLogoBinding): RecyclerView.ViewHolder(itemBinding.root) {
    fun bind() {
        val logoImage: ImageView = itemBinding.root
        logoImage.setImageDrawable(itemView.context.resources.getDrawable(R.drawable.logo_final_horizontal))
    }
}