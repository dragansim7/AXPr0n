package com.axpr0n

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.axpr0n.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ShortcutAdapter
    private var focusedPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        restoreFocus()
    }

    private fun setupRecyclerView() {
        adapter = ShortcutAdapter(ShortcutManager.shortcuts, this) { shortcut ->
            openBrowser(shortcut)
        }
        binding.gridRecyclerView.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = this@MainActivity.adapter
            setHasFixedSize(true)
            descendantFocusability = ViewGroup.FOCUS_AFTER_DESCENDANTS
        }
    }

    private fun restoreFocus() {
        binding.gridRecyclerView.post {
            binding.gridRecyclerView.findViewHolderForAdapterPosition(focusedPosition)?.itemView?.requestFocus()
        }
    }

    private fun openBrowser(shortcut: Shortcut) {
        focusedPosition = adapter.getSelectedPosition()
        startActivity(Intent(this, BrowserActivity::class.java).apply {
            putExtra("url", shortcut.url)
            putExtra("label", shortcut.label)
        })
    }

    override fun onResume() {
        super.onResume()
        restoreFocus()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return when (keyCode) {
            KeyEvent.KEYCODE_BACK -> {
                finishAffinity()
                true
            }
            else -> super.onKeyDown(keyCode, event)
        }
    }
}

class ShortcutAdapter(
    private val shortcuts: List<Shortcut>,
    private val context: MainActivity,
    private val onItemClick: (Shortcut) -> Unit
) : RecyclerView.Adapter<ShortcutAdapter.ShortcutViewHolder>() {

    private var selectedPosition = 0
    private val faviconFetcher = FaviconFetcher(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShortcutViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_shortcut, parent, false)
        return ShortcutViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShortcutViewHolder, position: Int) {
        val shortcut = shortcuts[position]
        holder.bind(shortcut, position)
    }

    override fun getItemCount(): Int = shortcuts.size

    fun getSelectedPosition(): Int = selectedPosition

    inner class ShortcutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val icon: ImageView = itemView.findViewById(R.id.tile_icon)
        private val label: TextView = itemView.findViewById(R.id.tile_label)
        private val container: View = itemView.findViewById(R.id.tile_container)

        fun bind(shortcut: Shortcut, position: Int) {
            label.text = shortcut.label
            container.setOnClickListener {
                selectedPosition = position
                onItemClick(shortcut)
            }
            container.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) selectedPosition = position
                updateFocusState(hasFocus)
            }
            loadIcon(shortcut)
        }

        private fun loadIcon(shortcut: Shortcut) {
            context.lifecycleScope.launch {
                val fallback = ContextCompat.getDrawable(context, R.drawable.ic_fallback) ?: return@launch
                val drawable = faviconFetcher.loadFavicon(shortcut.url, fallback)
                icon.setImageDrawable(drawable)
            }
        }

        private fun updateFocusState(hasFocus: Boolean) {
            if (hasFocus) {
                container.scaleX = 1.06f
                container.scaleY = 1.06f
                container.elevation = 12f
            } else {
                container.scaleX = 1f
                container.scaleY = 1f
                container.elevation = 4f
            }
        }
    }
}
