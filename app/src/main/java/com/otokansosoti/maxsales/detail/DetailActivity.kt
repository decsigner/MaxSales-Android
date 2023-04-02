package com.otokansosoti.maxsales.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.otokansosoti.maxsales.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        intent.getParcelableExtra<DetailBinding>("XModel")?.let { model ->
            binding.titleView.text = model.title
            val imageID = resources.getIdentifier(model.image, "drawable", packageName)
            binding.logoView.setImageDrawable(ContextCompat.getDrawable(this, imageID))

            binding.titleTextView.text = model.text

            binding.whatsappLabel.text = model.whatsappLabel
            binding.whatsappButton.text = model.whastappContent
            binding.whatsappButton.setOnClickListener { showWhatsAppPage() }

            binding.phoneLabel.text = model.phoneLabel
            binding.phoneButton.text = model.phoneContent
            binding.phoneButton.setOnClickListener { showCallPhone() }
        }
    }

    private fun clearWhatsApp(): String {
        return binding.whatsappButton.text.toString()
            .replace(" ", "")
            .replace("(", "")
            .replace(")", "")
            .replace("-", "")
    }

    private fun clearPhone(): String {
        return binding.phoneButton.text.toString().replace(" ", "")
    }

    private fun showCallPhone() {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:${clearPhone()}")
        startActivity(intent)
    }

    private fun showWhatsAppPage() {
        val appUrl = "https://api.whatsapp.com/send?phone=55${clearWhatsApp()}"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(appUrl)
        startActivity(intent)
    }
}