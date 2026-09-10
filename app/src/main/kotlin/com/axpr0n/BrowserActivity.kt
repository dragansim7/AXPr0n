package com.axpr0n

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.axpr0n.databinding.ActivityBrowserBinding

class BrowserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBrowserBinding
    private var currentUrl: String = ""
    private var currentLabel: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBrowserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currentUrl = intent.getStringExtra("url") ?: "about:blank"
        currentLabel = intent.getStringExtra("label") ?: "Browser"

        setupWebView()
        setupControls()
        loadUrl(currentUrl)
    }

    private fun setupWebView() {
        binding.webView.apply {
            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                databaseEnabled = true
                useWideViewPort = true
                loadWithOverviewMode = true
                builtInZoomControls = true
                displayZoomControls = false
                mixedContentMode = android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }
            webViewClient = CustomWebViewClient(this@BrowserActivity)
            webChromeClient = CustomWebChromeClient()
        }
    }

    private fun setupControls() {
        binding.controlBack.setOnClickListener { onBackPressed() }
        binding.controlReload.setOnClickListener { binding.webView.reload() }
        binding.controlHome.setOnClickListener { finish() }
    }

    private fun loadUrl(url: String) {
        binding.webView.loadUrl(url)
        updateControlTitle()
    }

    private fun updateControlTitle() {
        binding.controlTitle.text = currentLabel
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_DPAD_LEFT, KeyEvent.KEYCODE_DPAD_RIGHT,
            KeyEvent.KEYCODE_DPAD_UP, KeyEvent.KEYCODE_DPAD_DOWN -> {
                binding.webView.requestFocus()
                true
            }
            else -> super.onKeyDown(keyCode, event)
        }
    }

    inner class CustomWebViewClient(private val activity: BrowserActivity) : WebViewClient() {
        override fun onPageStarted(view: android.webkit.WebView?, url: String?, favicon: android.graphics.Bitmap?) {
            super.onPageStarted(view, url, favicon)
            binding.loadingIndicator.visibility = View.VISIBLE
        }

        override fun onPageFinished(view: android.webkit.WebView?, url: String?) {
            super.onPageFinished(view, url)
            binding.loadingIndicator.visibility = View.GONE
            url?.let { currentUrl = it }
        }

        override fun onReceivedError(
            view: android.webkit.WebView?,
            request: android.webkit.WebResourceRequest?,
            error: android.webkit.WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
            showErrorPage(error?.description?.toString() ?: "Unknown error")
        }
    }

    inner class CustomWebChromeClient : WebChromeClient() {
        override fun onProgressChanged(view: android.webkit.WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            binding.loadingIndicator.progress = newProgress
        }
    }

    private fun showErrorPage(message: String) {
        binding.loadingIndicator.visibility = View.GONE
        val errorHtml = """
            <html>
            <head><style>
                body { background: #111315; color: #F2F4F3; font-family: sans-serif; text-align: center; padding: 48dp; }
                h1 { margin-top: 100px; }
                button { background: #79B98A; color: #111315; border: none; padding: 16dp 32dp; margin: 16dp; cursor: pointer; font-size: 16sp; }
            </style></head>
            <body>
                <h1>Failed to Load</h1>
                <p>$message</p>
                <button onclick="location.reload()">Retry</button>
                <button onclick="window.history.back()">Go Back</button>
            </body>
            </html>
        """.trimIndent()
        binding.webView.loadData(errorHtml, "text/html", "UTF-8")
    }
}
