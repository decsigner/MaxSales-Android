package com.otokansosoti.maxsales.fragment.home.adapter

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.otokansosoti.maxsales.databinding.ViewholderHelpBinding
import com.otokansosoti.maxsales.databinding.ViewholderLogoBinding
import com.otokansosoti.maxsales.databinding.ViewholderProductBinding
import com.otokansosoti.maxsales.fragment.home.HomeModel
import com.otokansosoti.maxsales.fragment.home.viewHolder.HelpViewHolder
import com.otokansosoti.maxsales.fragment.home.viewHolder.LogoViewHolder
import com.otokansosoti.maxsales.fragment.home.viewHolder.product.ProductViewHolder
import com.otokansosoti.maxsales.fragment.home.viewHolder.product.ProductViewModel

class HomeAdapter (private val dataSource: List<HomeModel>, private val lifecycleOwner: LifecycleOwner, private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    private lateinit var mListener: onItemClickListener

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    private val LOGO = 0
    private val HELP = 1
    private val PRODUCT = 2

    private val openSac = MutableLiveData<Boolean>()
    private val openAbout = MutableLiveData<Boolean>()

    fun openSac() = openSac as LiveData<Boolean>
    fun openAbout() = openAbout as LiveData<Boolean>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        when (viewType) {
            LOGO -> {
                val itemBinding = ViewholderLogoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                viewHolder = LogoViewHolder(itemBinding)
            }
            HELP -> {
                val helpView = ViewholderHelpBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                val helpViewHolder = HelpViewHolder(helpView)
                helpViewHolder.openSac().observe(lifecycleOwner) { receiveOpenSac(it) }
                helpViewHolder.openAbout().observe(lifecycleOwner) { receiveOpenAbout(it) }
                viewHolder = helpViewHolder
            }
            else -> {
                val itemBinding = ViewholderProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                viewHolder = ProductViewHolder(itemBinding, mListener, context)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            LOGO -> {
                val view = holder as LogoViewHolder
                view.bind()
            }
            HELP -> {
                val view = holder as HelpViewHolder
                view.bind(dataSource[position])
            }
            else -> {
                val view = holder as ProductViewHolder
                view.bind(dataSource[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    override fun getItemViewType(position: Int): Int {
        val model = dataSource[position]
        if(model.type == "logo") {
            return LOGO
        } else if (model.type == "help") {
            return HELP
        } else if (model.type == "product") {
            return PRODUCT
        }

        return -1
    }

    private fun receiveOpenSac(show: Boolean) {
        openSac.value = show
    }

    private fun receiveOpenAbout(show: Boolean) {
        openAbout.value = show
    }
}