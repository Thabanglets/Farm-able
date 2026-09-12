# Fix Errors in MainActivity.kt

The goal is to resolve compilation errors and lifecycle issues in `MainActivity.kt` to ensure the Bottom Navigation works correctly and the app starts properly.

## Proposed Changes

### [app]

#### [MODIFY] [MainActivity.kt](file:///C:/Users/choki/AndroidStudioProjects/Farmable/app/src/main/java/com/example/farm_able/MainActivity.kt)
- Move `installSplashScreen()` before `super.onCreate(savedInstanceState)`.
- Replace `binding.bottomNav.setOnClickListener` with `binding.bottomNav.setOnItemSelectedListener`.
- Fix the `when` block to use `R.id.home` instead of `binding.home`.
- Handle `R.id.crop` as `CropFragment` also exists.
- Add a default fragment transaction to show `HomeFragment` on startup.
- Clean up unused imports.

## Verification Plan

### Automated Tests
- Run `gradle build` to ensure the project compiles.

### Manual Verification
- Deploy the app to a device/emulator.
- Verify that the splash screen shows (briefly).
- Verify that the Bottom Navigation correctly switches between Home and Crop fragments.
