# AXPr0n

An Android TV / Google TV shortcut launcher with a two-column D-pad home grid and an embedded WebView.

## Run & Operate

- Open `android-tv/` in Android Studio.
- Build with `gradle assembleDebug` from `android-tv/`, or use Android Studio's Build APK action.
- The original workspace API and mockup packages remain available for workspace tooling.

## Stack

- Kotlin
- Android Gradle Plugin 8.6.1
- Android Views + RecyclerView
- Android WebView

## Where things live

- `android-tv/app/src/main/java/com/axpr0n/tv/` — Kotlin application code
- `android-tv/app/src/main/res/` — manifest, layout, theme, colors, icons, and TV banner
- `android-tv/README.md` — concise Android Studio and APK build instructions

## Architecture decisions

- Classic Android Views are used instead of Compose for a small dependency footprint and explicit Android TV focus control.
- The home grid and browser are two states of one activity, so back behavior and focus restoration are deterministic.
- Shortcut data is centralized in `ShortcutCatalog`; icon fetching is optional and always has an in-app fallback.

## Product

- Presents seven website shortcut tiles in a predictable two-column D-pad grid.
- Opens shortcuts in a full-screen embedded WebView with back, reload, loading, and retry controls.

## Gotchas

- The Android project is under `android-tv/` and is independent of the pnpm workspace packages.
- A local Java 17 / Android SDK installation is required to compile the APK.

## Pointers

- See `android-tv/README.md` for the Android Studio and APK build steps.