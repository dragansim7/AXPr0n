package com.axpr0n

data class Shortcut(
    val label: String,
    val url: String,
    val faviconUrl: String? = null
)

object ShortcutManager {
    val shortcuts = listOf(
        Shortcut(
            label = "xHamster",
            url = "https://xhamster.com/",
            faviconUrl = "https://www.xhamster.com/favicon.ico"
        ),
        Shortcut(
            label = "SpankBang",
            url = "https://spankbang.com/",
            faviconUrl = "https://www.spankbang.com/favicon.ico"
        ),
        Shortcut(
            label = "PornDoe",
            url = "https://porndoe.com/",
            faviconUrl = "https://porndoe.com/favicon.ico"
        ),
        Shortcut(
            label = "PornDig",
            url = "https://porndig.com/",
            faviconUrl = "https://porndig.com/favicon.ico"
        ),
        Shortcut(
            label = "HQPorner",
            url = "https://m.hqporner.com/",
            faviconUrl = "https://hqporner.com/favicon.ico"
        ),
        Shortcut(
            label = "ePorner",
            url = "https://eporner.com/",
            faviconUrl = "https://eporner.com/favicon.ico"
        ),
        Shortcut(
            label = "PornTrex",
            url = "https://porntrex.com/",
            faviconUrl = "https://porntrex.com/favicon.ico"
        )
    )
}
