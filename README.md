# 📈 StockScreen – Android App

Built with Jetpack Compose that lets users browse and favorite their preferred stock. Designed with a clean UI and smooth user experience, this project demonstrates real-time UI updates, local storage for favorites, and pull-to-refresh functionality.

<br>

## 🌟 Features

- 🔍 Search for stocks by name or symbol  
- ⭐ Mark favorites and switch filters (All / Favourites)  
- 🔄 Pull-to-refresh with dynamic price changes  

---

## 📸 Demo Videos

### 🔍 Search & Refresh Demo  
🎬 [Click to watch](https://drive.google.com/file/d/1eBoWBN5sU_wkK85Tdz7xtiFdh0KAqfKu/view?usp=drive_link)

### ⭐ Favorite & Unfavorite  
🎬 [Click to watch](https://drive.google.com/file/d/1NGYepxS2R-XVvbWs50KCObuh9DDry-FD/view?usp=sharing)


---

## 🛠️ Tech Stack

- Kotlin + Jetpack Compose  
- Material3 Design  
- Accompanist SwipeRefresh  
- Coil for image loading  
- Gson for local data  

---

## 🚀 How to Run

1. Clone the repository:
git clone https://github.com/shmalini/StockApp.git

2. Open in Android Studio

3. Connect an emulator or device (Min SDK: 24, Target SDK: 35)

📥 **You may also [download the APK here](https://github.com/shmalini/StockApp/releases/download/v1.0-beta/Stock.apk)**  

---

## 📂 Project Structure Highlights

- `StockList.kt` — main UI logic with search, filter, and refresh  
- `StockItem.kt` — reusable stock card UI  
- `StockViewModel.kt` — handles data, filters, and state management  
- `assets/stocks.json` — preloaded stock data 
- Favorites are saved via **SharedPreferences** (no sign-in needed)

---

## Author
**Sharifah Malini**
