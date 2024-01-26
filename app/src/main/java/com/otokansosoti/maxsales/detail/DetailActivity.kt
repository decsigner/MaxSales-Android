package com.otokansosoti.maxsales.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
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


            if(model.title == "Assistência Medicamentos") {
                binding.titleTextView.text = HtmlCompat.fromHtml(model.text, HtmlCompat.FROM_HTML_MODE_LEGACY)
            } else {
                binding.titleTextView.text = model.text
            }

            if(model.whastappContent.isNotEmpty()) {
                binding.whatsappIcon.visibility = View.VISIBLE
                binding.whatsappLabel.visibility = View.VISIBLE
                binding.whatsappButton.visibility = View.VISIBLE

                binding.whatsappLabel.text = model.whatsappLabel
                binding.whatsappButton.text = model.whastappContent
                binding.whatsappButton.setOnClickListener { showWhatsAppPage() }
            }
            else {
                binding.whatsappIcon.visibility = View.INVISIBLE
                binding.whatsappLabel.visibility = View.INVISIBLE
                binding.whatsappButton.visibility = View.INVISIBLE
            }

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