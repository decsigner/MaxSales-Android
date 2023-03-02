package com.otokansosoti.maxsales.webview

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.otokansosoti.maxsales.databinding.ActivityWebviewBinding

class WebViewActivity: AppCompatActivity() {
    private lateinit var binding: ActivityWebviewBinding
    private lateinit var webview: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWebviewBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val url = intent.getStringExtra("XUrl")
        webview = binding.webview

        url?.let {
            webview.loadUrl(it)
            webview.settings.javaScriptEnabled = true
            webview.settings.setSupportZoom(true)
        }
    }

    override fun onBackPressed() {
        if(webview.canGoBack()) {
            webview.goBack()
        } else {
            super.onBackPressed()
        }
    }
}