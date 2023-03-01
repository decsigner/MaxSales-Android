package com.otokansosoti.maxsales.about

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.otokansosoti.maxsales.R
import com.otokansosoti.maxsales.databinding.ActivityAboutBinding

class AboutActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAboutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val aboutText = binding.aboutTextView
        aboutText.text = HtmlCompat.fromHtml(getString(R.string.about_text), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}