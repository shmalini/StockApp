package com.example.stockscreen

import android.content.SharedPreferences

class FavRepository(private val prefs: SharedPreferences) {

    private val KEY = "fav_symbols"

    fun getFavs(): Set<String> =
        prefs.getStringSet(KEY, emptySet()) ?: emptySet()

    fun toggle(sym: String) {
        val set = getFavs().toMutableSet().apply {
            if (contains(sym)) remove(sym) else add(sym)
        }
        prefs.edit().putStringSet(KEY, set).apply()
    }
}
