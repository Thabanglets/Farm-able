# Walkthrough - Fixed MainActivity.kt Errors

I have corrected the compilation errors and lifecycle issues in `MainActivity.kt`.

## Changes Made

### [MainActivity.kt](file:///C:/Users/choki/AndroidStudioProjects/Farmable/app/src/main/java/com/example/farm_able/MainActivity.kt)

- **Fixed SplashScreen Lifecycle**: Moved `installSplashScreen()` before `super.onCreate(savedInstanceState)`. This is required by the Splash Screen API to work correctly.
- **Corrected Bottom Navigation Listener**: Replaced `setOnClickListener` (which is for the view itself) with `setOnItemSelectedListener` (which provides the selected `MenuItem`).
- **Resolved Resource IDs**: Fixed the `when` block to use `R.id.home` and added support for `R.id.crop` (linking it to `CropFragment`).
- **Initial Fragment Loading**: Added logic to load `HomeFragment` by default when the app starts (if no saved state exists).
- **Code Cleanup**: Removed unused imports (`ViewCompat`, `WindowInsetsCompat`) and deleted the `Thread.sleep(500)` call which was blocking the main thread.

## Verification Results

### Automated Tests
- `analyze_file` returned no errors or warnings for `MainActivity.kt`.

### Manual Verification
- The app should now compile successfully.
- Bottom navigation will correctly swap between `HomeFragment` and `CropFragment`.
