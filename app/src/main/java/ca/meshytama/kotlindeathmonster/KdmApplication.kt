package ca.meshytama.kotlindeathmonster

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import ca.meshytama.kotlindeathmonster.model.KdmDatabase
import ca.meshytama.kotlindeathmonster.model.KdmDatabase.Initializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/**
 * Parent application class.
 * Setter of application-wide settings.
 * Holder of application-wide objects.
 */
class KdmApplication : Application(), CoroutineScope by MainScope() {

    lateinit var database: KdmDatabase

    override fun onCreate() {
        super.onCreate()

        // Forces the app to always appear in night mode, which suits the KD:M style much more closely.
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        val dbInitializer = object : Initializer(this@KdmApplication) {
            override fun getDatabase() = database
        }
        database = KdmDatabase.Factory.createDatabase("kdm_database", this, dbInitializer)

        // On the first run, the database won't be pre-populated until the first actual query is run.
        // We kick off a small query on application start just to jump-start the process.
        // TODO this doesn't necessarily mean that the DB is ready by the time the user clicks on a page
        // We could gate the ability to open any further pages behind a flag that is set when onCreate()
        // finishes, but we would need to make sure that we don't softlock the app if that flag were to
        // be cleared accidentally.
        launch(Dispatchers.IO) {
            database.expansionsDao().getAll()
        }

    }
}