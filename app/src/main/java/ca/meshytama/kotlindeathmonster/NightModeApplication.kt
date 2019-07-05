package ca.meshytama.kotlindeathmonster

import android.app.Application
import android.support.v7.app.AppCompatDelegate

/**
 * This subclass of the application exists for the sole purpose of always running the app in night mode.
 */
@Suppress("unused") // Despite what lint seems to think, this class really is referenced from the manifest.
class NightModeApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }
}