package ca.meshytama.kotlindeathmonster

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import ca.meshytama.kotlindeathmonster.model.KdmDatabase
import ca.meshytama.kotlindeathmonster.model.KdmDatabase.Initializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@Suppress("unused") // Despite what lint seems to think, this class really is referenced from the manifest.
class KdmApplication:Application(), CoroutineScope by MainScope(){

    lateinit var database: KdmDatabase

    override fun onCreate() {
        super.onCreate()

        // Forces the app to always appear in night mode, which suits the KD:M style much more closely.
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        // TODO expl?
        val dbInitializer = object : Initializer(this@KdmApplication) {
            override fun getDatabase(): KdmDatabase {
                return database
            }
        }
        database = KdmDatabase.Factory.createDatabase("kdm_database", this, dbInitializer)

        //
        launch(Dispatchers.IO) {
            database.expansionsDao().getAll()
        }

    }
}