package com.axpr0n

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import coil.ImageLoader
import coil.request.ImageRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import java.net.URL

class FaviconFetcher(private val context: Context) {
    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(5, java.util.concurrent.TimeUnit.SECONDS)
        .readTimeout(5, java.util.concurrent.TimeUnit.SECONDS)
        .build()

    suspend fun loadFavicon(url: String, fallbackDrawable: Drawable): Drawable = withContext(Dispatchers.IO) {
        return@withContext try {
            val faviconUrl = extractFaviconUrl(url) ?: return@withContext fallbackDrawable
            loadFromUrl(faviconUrl) ?: fallbackDrawable
        } catch (e: Exception) {
            fallbackDrawable
        }
    }

    private suspend fun extractFaviconUrl(pageUrl: String): String? = withContext(Dispatchers.IO) {
        return@withContext try {
            val request = Request.Builder().url(pageUrl).build()
            httpClient.newCall(request).execute().use { response ->
                if (response.isSuccessful) {
                    val html = response.body?.string() ?: return@withContext null
                    val doc = Jsoup.parse(html, pageUrl)
                    doc.select("link[rel~=icon]").firstOrNull()?.attr("href")
                        ?: doc.select("link[rel*=apple-touch-icon]").firstOrNull()?.attr("href")
                        ?: resolveUrl(pageUrl, "/favicon.ico")
                } else null
            }
        } catch (e: Exception) {
            try {
                resolveUrl(pageUrl, "/favicon.ico")
            } catch (e: Exception) {
                null
            }
        }
    }

    private fun resolveUrl(baseUrl: String, path: String): String {
        val base = URL(baseUrl)
        return URL(base.protocol, base.host, base.port, path).toString()
    }

    private suspend fun loadFromUrl(iconUrl: String): Drawable? = withContext(Dispatchers.IO) {
        return@withContext try {
            val request = ImageRequest.Builder(context)
                .data(iconUrl)
                .size(coil.size.Size.ORIGINAL)
                .build()
            ImageLoader(context).execute(request).drawable
        } catch (e: Exception) {
            null
        }
    }
}
