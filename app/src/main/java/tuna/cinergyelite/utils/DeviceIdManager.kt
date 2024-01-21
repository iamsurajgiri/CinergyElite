package tuna.cinergyelite.utils


import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.provider.Settings
import tuna.core.constansts.Constants
import java.util.UUID

class DeviceIdManager {
    companion object {
        private lateinit var sharedPreferences: SharedPreferences

        fun init(context: Context) {
            sharedPreferences =
                context.getSharedPreferences(Constants.DEVICE_ID_PREFS, Context.MODE_PRIVATE)
            setDeviceId(context)
        }

        fun getDeviceId(): String {
            val defaultId = "${Build.MANUFACTURER}${Build.MODEL}${UUID.randomUUID()}"
            val deviceId = sharedPreferences.getString(Constants.DEVICE_HASH_ID, defaultId)
                ?.replace(" ", "") ?: defaultId.replace(" ", "")
            return "CI - $deviceId"
        }

        private fun setDeviceId(context: Context) {
            val deviceId = sharedPreferences.getString(Constants.DEVICE_HASH_ID, null)

            if (deviceId == null) {
                val newDeviceId = generateDeviceId(context)
                sharedPreferences.edit().putString(Constants.DEVICE_HASH_ID, newDeviceId).apply()
            }
        }

        private fun generateDeviceId(context: Context): String {
            val defaultId = "${Build.MANUFACTURER}${Build.MODEL}${UUID.randomUUID()}"
            val androidId =
                Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
            val deviceId = if (androidId.isNullOrEmpty()) defaultId else "$androidId.$defaultId"

            // Trim deviceId if it's more than 100 characters
            return deviceId.replace(" ", "").take(100)
        }

    }
}

