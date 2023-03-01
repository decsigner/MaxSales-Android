package com.otokansosoti.maxsales.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.otokansosoti.maxsales.databinding.FragmentContactsBinding

class ContactsFragment : Fragment() {
    private lateinit var binding: FragmentContactsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentContactsBinding.inflate(inflater, container, false)
        setupActions()
        return binding.root
    }

    private fun setupActions() {
        val sacButton: Button = binding.sacButton
        val phoneURI = Uri.parse("tel:08005911946")
        sacButton.setOnClickListener {
            val dialIntent = Intent(Intent.ACTION_DIAL).also {
                it.setData(phoneURI)
            }
            startActivity(dialIntent)
        }

        val mailButton: Button = binding.emailButton
        mailButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "plain/text"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("contato@salesmax.com.br"))
            startActivity(Intent.createChooser(intent, ""))
        }

        val siteButton: Button = binding.siteButton
        siteButton.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.salesmax.com.br"))
            startActivity(browserIntent)
        }
    }
}