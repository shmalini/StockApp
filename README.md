# 📈 StockScreen – Android App
Built with Jetpack Compose that lets users browse and favorite financial assets like stocks. Designed with a clean UI and smooth user experience, this project demonstrates real-time UI updates, local storage for favorites, and pull-to-refresh functionality.

<br>
##🌟 Features
🔍 Search for stocks by name or symbol

⭐ Mark favorites, stored locally

🔄 Pull-to-refresh with dynamic price changes

🧭 Live filter switching (All / Favorites)

🖼️ Stock logos via remote URL

🌓 Forces Light Mode for consistent visual clarity

📱 Optimized UI with Material 3 & Compose

---

<br>
## 📸 Demo Videos
Search & Refresh Demo
*video*

Favorite & Unfavorite Flow
*video*

<br>
## 🛠️ Tech Stack
Kotlin + Jetpack Compose

Material3 Design

Accompanist SwipeRefresh

Coil for image loading

Gson for local data

<br>
## 🚀 How to Run
Clone the repository:

```bash
git clone https://github.com/shmalini/StockApp.git
Open in Android Studio

Connect an emulator or device (Min SDK: 24, Target SDK: 35)


## You may also download the APK here: *link*


<br>
## 📂 Project Structure Highlights
StockList.kt — main UI logic with search, filter, and refresh

StockItem.kt — reusable stock card UI

StockViewModel.kt — handles data, filters, and state management

assets/stocks.json — preloaded stock data (no API)

Favorites are saved via SharedPreferences (no sign-in needed)


<br>
## Author
Sharifah Malini