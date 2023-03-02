package com.otokansosoti.maxsales.product.viewholder

import android.content.Context
import android.graphics.BitmapFactory
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.otokansosoti.maxsales.R
import com.otokansosoti.maxsales.databinding.ViewholderProductBinding
import com.otokansosoti.maxsales.fragment.home.HomeModel
import com.otokansosoti.maxsales.fragment.home.adapter.HomeAdapter
import com.otokansosoti.maxsales.network.Endpoint
import com.otokansosoti.maxsales.network.Network
import com.otokansosoti.maxsales.product.adapter.ProductListAdapter
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductItemViewHolder(private val itemBinding: ViewholderProductBinding, listener: ProductListAdapter.onItemClickListener, private val context: Context): RecyclerView.ViewHolder(itemBinding.root), View.OnClickListener {
    private val iconProduct: ImageView = itemBinding.iconProduct
    private val titleLabel: TextView = itemBinding.titleLabel
    private val infoLabel: TextView = itemBinding.infoLabel

    init {
        itemView.setOnClickListener {
            listener.onItemClick(adapterPosition)
        }
    }

    fun bind(model: HomeModel) {
        loadImage(model.image)
        titleLabel.text = model.title
        infoLabel.text = model.info
    }

    private fun loadImage(imageName: String) {
        val baseUrl = context.applicationContext.resources.getString(R.string.base_url)
        val server = Network.getRetrofitInstance(baseUrl)
        val endpoint = server.create(Endpoint::class.java)
        val callback = endpoint.getImage(imageName)

        callback.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.code() == 200) {
                    response.body()?.let {
                        val bytes = it.bytes()
                        iconProduct.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.size))
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    override fun onClick(p0: View?) {}
}