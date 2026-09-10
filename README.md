# AXPr0n - Android TV Shortcut Launcher

A lightweight Android TV application that provides quick access to web shortcuts through an intuitive grid-based interface. Perfect for creating a personalized home screen with your favorite websites and applications.

## Features

- **Grid-based Shortcut Interface**: 2x2 grid layout for easy navigation
- **Favicon Integration**: Automatically loads favicons from shortcut URLs
- **Built-in Web Browser**: Integrated WebView with navigation controls
- **D-Pad Navigation**: Full support for Android TV remote controls
- **Focus Management**: Intelligent focus handling with visual feedback
- **Error Handling**: User-friendly error pages for failed page loads
- **Material Design**: Clean, modern UI with proper theming
- **View Binding**: Type-safe view access with Android's ViewBinding

## Architecture

### Project Structure

```
AXPr0n/
├── app/
│   ├── src/main/
│   │   ├── kotlin/com/axpr0n/
│   │   │   ├── MainActivity.kt           # Home screen with shortcut grid
│   │   │   ├── BrowserActivity.kt        # Web browser with controls
│   │   │   ├── Shortcut.kt               # Data class for shortcuts
│   │   │   ├── ShortcutManager.kt        # Manages shortcut data
│   │   │   └── FaviconFetcher.kt         # Fetches favicons from URLs
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml     # Main activity layout
│   │   │   │   ├── activity_browser.xml  # Browser activity layout
│   │   │   │   └── item_shortcut.xml     # Shortcut tile layout
│   │   │   ├── values/
│   │   │   │   ├── strings.xml           # String resources
│   │   │   │   ├── dimens.xml            # Dimension resources
│   │   │   │   └── colors.xml            # Color resources
│   │   │   └── drawable/
│   │   │       ├── tile_background.xml   # Tile background shape
│   │   │       ├── progress_bar.xml      # Loading indicator
│   │   │       └── ic_fallback.xml       # Fallback icon
│   │   └── AndroidManifest.xml
│   ├── build.gradle.kts                  # Module-level build config
│   └── proguard-rules.pro                # ProGuard rules
├── build.gradle.kts                      # Project-level build config
├── settings.gradle.kts                   # Gradle settings
├── .github/workflows/
│   └── build.yml                         # CI/CD workflow
├── .gitignore                            # Git ignore rules
└── LICENSE                               # MIT License

```

### Key Components

#### MainActivity
- Displays grid of shortcut tiles
- Handles navigation between shortcuts
- Uses RecyclerView with GridLayoutManager (2 columns)
- Manages focus state and restoration

#### BrowserActivity
- Embeds Android WebView for browsing
- Provides back/reload/home controls
- Shows loading indicator during page loads
- Displays error pages for failed requests

#### ShortcutAdapter
- Custom RecyclerView adapter for shortcuts
- Loads favicons asynchronously
- Updates focus state with visual scaling
- Handles click events

## Setup Instructions

### Prerequisites

- **Android Studio** (Arctic Fox or later)
- **JDK 11** or higher
- **Android SDK 28** or higher
- **Gradle 7.0** or higher

### Build from Source

1. **Clone the repository**
   ```bash
   git clone https://github.com/dragansim7/AXPr0n.git
   cd AXPr0n
   ```

2. **Open in Android Studio**
   - File → Open → Select the AXPr0n directory
   - Android Studio will sync Gradle automatically

3. **Configure Shortcuts** (Optional)
   Edit `ShortcutManager.kt` to customize shortcuts:
   ```kotlin
   object ShortcutManager {
       val shortcuts = listOf(
           Shortcut("YouTube", "https://youtube.com", R.drawable.ic_youtube),
           Shortcut("Netflix", "https://netflix.com", R.drawable.ic_netflix),
           // Add more shortcuts...
       )
   }
   ```

4. **Build the APK**
   - Debug: `./gradlew assembleDebug`
   - Release: `./gradlew assembleRelease`

5. **Install on Device**
   ```bash
   # For debug APK
   ./gradlew installDebug
   
   # For release APK (requires signed keystore)
   ./gradlew installRelease
   ```

## Building APKs

### Debug Build
```bash
./gradlew assembleDebug
```
Output: `app/build/outputs/apk/debug/app-debug.apk`

### Release Build
```bash
./gradlew assembleRelease
```
Requires signing configuration. See [Android Signing Guide](https://developer.android.com/studio/publish/app-signing)

Output: `app/build/outputs/apk/release/app-release.apk`

## Automated Builds (GitHub Actions)

This project includes a GitHub Actions workflow that automatically:
- Builds the APK on every push to `main`
- Runs on pull requests
- Uploads artifacts for 30 days
- Creates releases when tags are pushed

Workflow file: `.github/workflows/build.yml`

### Manual Workflow Trigger
1. Go to Actions tab
2. Select "Build Android APK"
3. Click "Run workflow"

## Dependencies

### Core Libraries
- **androidx.appcompat:appcompat** - AppCompat support
- **androidx.recyclerview:recyclerview** - RecyclerView for grid layout
- **androidx.constraintlayout:constraintlayout** - ConstraintLayout
- **androidx.lifecycle:lifecycle-runtime-ktx** - Lifecycle management
- **org.jetbrains.kotlinx:kotlinx-coroutines** - Coroutines for async tasks

### View Binding
- **androidx.viewbinding:viewbinding** - Type-safe view access

All dependencies are defined in `app/build.gradle.kts`

## Configuration

### Dimensions & Spacing
Edit `app/src/main/res/values/dimens.xml`:
```xml
<dimen name="tile_size">160dp</dimen>
<dimen name="icon_size">80dp</dimen>
<dimen name="grid_spacing">16dp</dimen>
```

### Colors & Theming
Edit `app/src/main/res/values/colors.xml`:
```xml
<color name="background_charcoal">#111315</color>
<color name="tile_surface">#1A1C1E</color>
<color name="accent_green">#79B98A</color>
<color name="text_primary">#F2F4F3</color>
```

## Testing

### Running Tests
```bash
./gradlew test           # Unit tests
./gradlew connectedAndroidTest  # Instrumented tests
```

### Testing on Android TV Emulator
1. Create Android Virtual Device (API 28+)
2. Select "TV" as device type
3. Run: `./gradlew installDebug`
4. Navigate using D-Pad

## Troubleshooting

### Build Fails
- Ensure JDK 11+ is installed
- Run: `./gradlew clean build`
- Check Android SDK is properly installed

### APK Won't Install
- Ensure target device is Android 9.0 (API 28) or higher
- Check device has developer mode and USB debugging enabled
- Use: `adb install app/build/outputs/apk/release/app-release.apk`

### WebView Issues
- Ensure device has updated Google Chrome or Android System WebView
- Check internet connection on device
- Verify URLs are accessible

### Favicon Not Loading
- Check internet connectivity
- Verify URL is valid and accessible
- Favicon loading happens asynchronously - wait for it to complete

## Performance Optimization

- **Favicon Caching**: Favicons are cached after first load
- **Focus Restoration**: Last focused position is restored on resume
- **Lazy Loading**: Images loaded on-demand in RecyclerView
- **Coroutine Management**: Proper lifecycle-aware coroutine scope usage

## Contributing

Contributions are welcome! Please:
1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Submit a pull request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Author

**dragansim7** - Initial development

## Support

For issues, questions, or suggestions:
- Open an issue on [GitHub Issues](https://github.com/dragansim7/AXPr0n/issues)
- Check existing documentation and README
- Review code comments for implementation details

## Changelog

### Version 1.0.0 (2026-09-10)
- Initial release
- Grid-based shortcut interface
- Integrated WebView browser
- Favicon loading
- D-Pad navigation support
- GitHub Actions CI/CD workflow
- Full documentation

---

**Happy browsing on your Android TV! 📺**
