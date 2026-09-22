# Farm-able 🌾

Farm-able is a modern, clean, and intuitive Android application designed to assist small-scale farmers in managing their day-to-day operations, monitoring crop health, tracking live weather patterns, and managing agricultural workflows with ease.

---

## 🚀 Key Features

### 1. 📅 Task Management
- View **Today's Farm Tasks** via a dashboard widget or a full screen.
- Clear filtering sections split into **Pending** and **Completed** workflows.
- View critical indicators like task urgency (`HIGH`, `MEDIUM`, `LOW`), custom timelines (`Today`, `Tomorrow`), and actionable reasoning alerts ("Why?").
- Instantly update status loops in-memory using **Complete** or **Skip** button controls.

### 2. 🥬 Crop Profile Trackers
- Fully dynamic crop listings matching preferences selected during the user onboarding phase (Tomato, Pepper, Spinach, Cabbage).
- Custom metrics monitoring specific growth cycles (e.g., `Fruit Development`, `Head Formation`) and color-coded crop health markers (Green $\ge$ 80%, Orange $\ge$ 60%, Red otherwise).
- Keep track of scheduling details including precise planting and estimated harvest target dates.

### 3. ⚙️ Smart Custom Settings
- Accessible seamlessly by tapping the green logo icon located on the top-right header of your home dashboard.
- Toggle controls for customizing **Push Notifications** and **Weather Risk Alerts**.
- Inline interactive preferences to modify app language selections or view the software release lifecycle version.

### 4. ☀️ Synchronized Live Weather Analytics
- Syncs automatically with onboarding entries to query atmospheric metrics for your exact town or city.
- Displays current temperature indices, weather conditions, local timestamps, and wind metrics.
- Presents a vertical scrolling **3-Hour Interval Weather Forecast** list perfectly integrated underneath the main summary cards.

### 5. 📸 AI Plant Scan Diagnosis Reporting
- Upload an existing image from your gallery or use your device camera to capture symptoms on leaves, fruit, or stems.
- Connects directly to the **PlantNet API** to evaluate potential pathologies.
- Renders detailed results on a dedicated **Scan Results Page** including disease name, confidence score percentage metrics, hazard categories, and immediate agricultural care strategies.

---

## 🛠️ Tech Stack & Architecture

- **Language**: 100% Kotlin with modern asynchronous coroutines workflow processing.
- **UI Framework**: Native Android View layouts styled with XML and unified Google Material 3 components.
- **Asynchronous Loop Handling**: Kotlin Coroutines with lifecycle-aware execution scopes (`lifecycleScope`, `viewLifecycleOwner`).
- **Network Interface**: Retrofit 2 + Gson converters interacting directly with OpenWeatherMap and PlantNet API endpoints.
- **Session Cache**: Local Android `SharedPreferences` managing active user states, persistent location variables, and onboarding flags.

---

## 🔧 Installation & Setup

1. Clone or copy the project into your local development machine environment.
2. Open the directory structure inside **Android Studio**.
3. Allow Gradle to synchronize dependencies (`build.gradle`).
4. Select an active Android Emulator or plug in a hardware developer device.
5. Tap **Run App** (`app`) to test and compile.
