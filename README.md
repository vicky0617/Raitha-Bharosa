# Raitha Bharosa Hub 🌾

**Raitha Bharosa Hub** is a comprehensive Android application designed to empower farmers with real-time information, digital tools, and personalized agricultural guidance. Built with modern Android technologies, the app aims to bridge the gap between traditional farming and technology.

## 🚀 Features

- **Multi-language Support:** Seamlessly switch between regional languages to ensure accessibility for all farmers.
- **Real-time Weather Updates:** Stay informed with local weather forecasts and alerts to plan farming activities effectively.
- **Agriculture Calendar:** Track and manage daily farming activities with a built-in calendar and reminders.
- **Soil Monitoring:** Insights into soil health and recommendations for better crop yields.
- **7-Day Krishi Action Plan:** Personalized action plans to guide farmers through critical crop cycles.
- **Crop History:** Maintain a digital record of previous crops, yields, and practices.
- **Map Integration:** Visualize farm locations and surrounding agricultural resources.
- **Notifications:** Timely alerts for weather changes, activity reminders, and agricultural news.
- **Farmer Profile:** Secure registration and management of farmer details and preferences.

## 🛠️ Tech Stack

- **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose) - Modern toolkit for building native UI.
- **Navigation:** [Compose Navigation](https://developer.android.com/jetpack/compose/navigation) - Seamless screen transitions.
- **Concurrency:** [Kotlin Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://kotlinlang.org/docs/flow.html).
- **Local Database:** [Room](https://developer.android.com/training/data-storage/room) - Robust local data persistence.
- **Background Tasks:** [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager) - Reliable background processing for weather updates.
- **Data Storage:** [DataStore](https://developer.android.com/topic/libraries/architecture/datastore) - Modern alternative to SharedPreferences for user settings.
- **Maps:** [Google Maps Compose](https://github.com/googlemaps/android-maps-compose) - Integrating interactive maps.
- **Camera:** [CameraX](https://developer.android.com/training/camerax) - Easy-to-use camera integration for crop monitoring.
- **Architecture:** MVVM (Model-View-ViewModel) - Clean and scalable architecture.

## 📦 Project Structure

```text
com.example.raithabharosahub/
├── data/
│   ├── local/          # Room database, DAOs, and DataStore
├── ui/
│   ├── navigation/     # NavGraph and Screen definitions
│   ├── screens/        # Compose screens for each feature
│   ├── theme/          # Material 3 Theme definitions
│   └── viewmodels/     # ViewModels for business logic
├── worker/             # Background workers (e.g., WeatherWorker)
└── utils/              # Helper classes and extensions
```

## 🏁 Getting Started

### Prerequisites

- Android Studio Jellyfish | 2023.3.1 or newer
- JDK 17
- Android SDK 24+

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/RaithaBharosaHub.git
   ```
2. Open the project in Android Studio.
3. Sync the project with Gradle files.
4. Run the app on an emulator or a physical device.

## 📸 Screenshots
<img width="150" height="300" alt="image" src="https://github.com/user-attachments/assets/f4dfbcf1-4881-4229-a3e1-70986827c33a" />
<img width="150" height="300" alt="image" src="https://github.com/user-attachments/assets/a78bed74-3e31-4005-b77a-c373e4d148d6" />
<img width="150" height="300" alt="image" src="https://github.com/user-attachments/assets/2dc2a1be-2efe-4a1b-9a2a-58b4e083270e" />
         | Splash Screen | Onboarding | Dashboard |



## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---
Developed with ❤️ for the farming community.
