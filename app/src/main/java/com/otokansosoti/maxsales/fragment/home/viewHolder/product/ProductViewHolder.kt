package com.otokansosoti.maxsales.fragment.home.viewHolder.product

import android.app.Application
import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.otokansosoti.maxsales.R
import com.otokansosoti.maxsales.databinding.ViewholderProductBinding
import com.otokansosoti.maxsales.fragment.home.HomeModel
import com.otokansosoti.maxsales.fragment.home.adapter.HomeAdapter

class ProductViewHolder(private val itemBinding: ViewholderProductBinding, listener: HomeAdapter.onItemClickListener, private val viewModel: ProductViewModel, private val lifecycleOwner: LifecycleOwner,): RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {

    private val iconProduct: ImageView = itemBinding.iconProduct
    private val titleLabel: TextView = itemBinding.titleLabel
    private val infoLabel: TextView = itemBinding.infoLabel

    init {
        itemView.setOnClickListener {
            listener.onItemClick(adapterPosition)
        }
    }

    fun bind(model: HomeModel) {
        viewModel.showBitMapIMage().observe(lifecycleOwner) { setIcon(it) }
        viewModel.loadImage(model.image)

        titleLabel.text = model.title
        infoLabel.text = model.info
    }

    fun setIcon(bitmap: Bitmap) {
        iconProduct.setImageBitmap(bitmap)
    }

    override fun onClick(p0: View?) {}
}