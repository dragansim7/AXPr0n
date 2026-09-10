# AXPr0n – Android TV Link Launcher

A production-ready Android TV / Google TV application that displays a clean grid of website shortcut tiles. Select a tile with your remote to open the website in an embedded in-app WebView browser.

## Features

- **Android TV optimized**: Full D-pad remote compatibility with predictable focus navigation
- **Minimalist design**: Dark charcoal background (#111315) with muted green accents (#79B98A)
- **2-column grid**: Shortcut tiles for 7 popular websites, displayed on a single home screen
- **Embedded WebView**: All website navigation happens within the app—no external browser
- **Smart back behavior**: Navigate back within the WebView, then to home, then exit the app
- **Loading feedback**: Minimal loading indicator during page load
- **TV-friendly typography**: Large, readable text and generous spacing for 10-foot viewing distance
- **Graceful error handling**: Retry and return-to-home options for failed pages

## Supported Websites (in order)

1. xHamster
2. SpankBang
3. PornDoe
4. PornDig
5. HQPorner
6. ePorner
7. PornTrex

## Build Instructions

### Prerequisites

- Android Studio 2024.1 or later
- Android SDK API 28–34
- Gradle 8.x

### Steps

1. **Clone the repository**:
   ```bash
   git clone https://github.com/dragansim7/AXPr0n.git
   cd AXPr0n
   ```

2. **Open in Android Studio**:
   - Launch Android Studio
   - Select "Open" and choose the `AXPr0n` directory
   - Wait for Gradle sync to complete

3. **Build the APK**:
   ```bash
   ./gradlew assembleRelease
   ```
   or use Android Studio's **Build** > **Build Bundle(s) / APK(s)** > **Build APK(s)**

4. **Install on Android TV**:
   ```bash
   adb install -r app/build/outputs/apk/release/app-release.apk
   ```

5. **Run on Emulator**:
   - Create an Android TV emulator (e.g., "Android TV (1080p)")
   - Select it as the target device
   - Press **Run** in Android Studio

## Project Structure

```
AXPr0n/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── kotlin/com/axpr0n/
│   │   │   │   ├── MainActivity.kt          # Home grid screen
│   │   │   │   ├── BrowserActivity.kt       # WebView browser screen
│   │   │   │   ├── ShortcutManager.kt       # Shortcut data model
│   │   │   │   └── FaviconFetcher.kt        # Icon/favicon loading utility
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml
│   │   │   │   │   └── activity_browser.xml
│   │   │   │   ├── drawable/
│   │   │   │   │   └── ic_fallback.xml      # Fallback tile icon
│   │   │   │   ├── values/
│   │   │   │   │   ├── colors.xml
│   │   │   │   │   ├── dimens.xml
│   │   │   │   │   └── strings.xml
│   │   │   │   └── AndroidManifest.xml
│   │   └── test/
│   ├── build.gradle.kts
│   └── proguard-rules.pro
├── build.gradle.kts
├── gradle.properties
├── settings.gradle.kts
├── README.md
└── LICENSE
```

## Key Files

- **MainActivity.kt**: Home screen with 2-column grid of tiles and focus navigation
- **BrowserActivity.kt**: Full-screen WebView with top control bar (back, title, reload)
- **ShortcutManager.kt**: Data model defining all websites, labels, and URLs
- **FaviconFetcher.kt**: Async icon downloading with fallback support
- **AndroidManifest.xml**: TV launcher configuration, permissions, and intent filters

## Configuration

### Editing Shortcuts

Open `ShortcutManager.kt` and modify the `shortcuts` list:

```kotlin
val shortcuts = listOf(
    Shortcut("xHamster", "https://xhamster.com/", "https://..."),
    Shortcut("SpankBang", "https://spankbang.com/", "https://..."),
    // ...
)
```

### Customizing Colors

Edit `res/values/colors.xml`:

```xml
<color name="background_charcoal">#111315</color>
<color name="tile_surface">#1B1F21</color>
<color name="accent_green">#79B98A</color>
<color name="text_primary">#F2F4F3</color>
<color name="text_secondary">#A8B0AD</color>
```

### Adjusting Dimensions

Edit `res/values/dimens.xml` for tile size, spacing, margins, and corner radius.

## TV Remote Controls

| Button | Action |
|--------|--------|
| **D-Pad Up/Down** | Move focus between rows |
| **D-Pad Left/Right** | Move focus between columns |
| **Centre/Select** | Open focused tile in browser |
| **Back** | Exit browser (if history exists) or return to home / exit app |

## Technical Details

- **Language**: Kotlin
- **Min SDK**: API 28 (Android 9)
- **Target SDK**: API 34 (Android 15)
- **Architecture**: MVVM-lite with Coroutines
- **UI Framework**: Android Views + RecyclerView (GridLayoutManager)
- **WebView**: Android System WebView with JavaScript and DOM storage enabled
- **Manifest Features**:
  - `android.software.leanback` (Android TV support)
  - `android.hardware.touchscreen` not required
  - Intent category: `android.intent.category.LEANBACK_LAUNCHER`

## Permissions

- `android.permission.INTERNET` — Required to load websites
- No analytics, ads, tracking, or account permissions

## Gradle Dependencies

- `androidx.appcompat:appcompat`
- `androidx.constraintlayout:constraintlayout`
- `androidx.recyclerview:recyclerview`
- `androidx.lifecycle:lifecycle-runtime-ktx`
- `androidx.lifecycle:lifecycle-viewmodel-ktx`
- `androidx.lifecycle:lifecycle-livedata-ktx`
- `kotlinx.coroutines:coroutines-android`
- `com.squareup.okhttp3:okhttp` (for favicon fetching)
- `org.jsoup:jsoup` (for HTML parsing, optional)
- Coil (for image loading) or manual implementation

## License

All rights reserved. See LICENSE for details.

---

**Version**: 1.0  
**Last Updated**: 2026-09-10
