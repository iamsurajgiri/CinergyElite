package tuna.cinergyelite.main

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import tuna.cinergyelite.di.appModule
import tuna.cinergyelite.di.networkModule
import tuna.cinergyelite.utils.DeviceIdManager
import tuna.cinergyelite.utils.PreferenceHelper

class CinergyElite : Application() {

    override fun onCreate() {
        super.onCreate()
        PreferenceHelper.init(this)
        DeviceIdManager.init(this)
        startKoin {
            androidContext(this@CinergyElite)
            modules(listOf(networkModule, appModule))
        }
    }
}