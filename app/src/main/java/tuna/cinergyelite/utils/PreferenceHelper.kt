package tuna.cinergyelite.utils

import android.content.Context
import android.content.SharedPreferences
import tuna.core.constansts.Constants.CINERGY_PREF_KEY

class PreferenceHelper {
    companion object {

        private lateinit var preferences: SharedPreferences

        fun init(context: Context) {
            preferences = context.getSharedPreferences(CINERGY_PREF_KEY, Context.MODE_PRIVATE)
        }

        fun saveString(key: String, value: String) {
            preferences.edit().putString(key, value).apply()
        }

        fun getString(key: String, default: String = ""): String {
            return preferences.getString(key, default) ?: default
        }

        fun getInt(key: String, default: Int = -1): Int {
            return preferences.getInt(key, default)
        }

        fun saveInt(key: String, number: Int) {
            preferences.edit().putInt(key, number).apply()
        }

        fun clearData() {
            preferences.edit().clear().apply()
        }

    }
}