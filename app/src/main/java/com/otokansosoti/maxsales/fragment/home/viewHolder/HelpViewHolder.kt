package com.otokansosoti.maxsales.fragment.home.viewHolder

import android.content.Context
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.otokansosoti.maxsales.R
import com.otokansosoti.maxsales.databinding.ViewholderHelpBinding
import com.otokansosoti.maxsales.fragment.home.HomeModel
import com.otokansosoti.maxsales.fragment.home.adapter.HomeAdapter
import kotlin.math.roundToInt

class HelpViewHolder(private val itemBinding: ViewholderHelpBinding): RecyclerView.ViewHolder(itemBinding.root) {

    private val openSac = MutableLiveData<Boolean>()
    private val openAbout = MutableLiveData<Boolean>()

    fun openSac() = openSac as LiveData<Boolean>
    fun openAbout() = openAbout as LiveData<Boolean>

    fun bind(model: HomeModel) {
        val firstImage: ImageButton = itemBinding.firstImage
        val secondImage: ImageButton = itemBinding.secondImage

        val displayMetrics = DisplayMetrics()
        val windowManager: WindowManager = itemView.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        firstImage.layoutParams.width = (displayMetrics.widthPixels * 0.5).roundToInt() - 50
        secondImage.layoutParams.width = (displayMetrics.widthPixels * 0.5).roundToInt() - 50

        firstImage.setOnClickListener {
            openAbout.value = true
        }

        secondImage.setOnClickListener {
            openSac.value = true
        }
    }
}