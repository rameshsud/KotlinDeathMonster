package ca.meshytama.kotlindeathmonster.model

import android.content.Context
import android.util.Log
import androidx.annotation.RawRes
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import ca.meshytama.kotlindeathmonster.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONArray

@Database(entities = [Expansion::class, Disorder::class, FightingArt::class, FightingArtType::class, GlossaryEntry::class], version = 1)
abstract class KdmDatabase : RoomDatabase() {
    abstract fun expansionsDao(): ExpansionsDao
    abstract fun disordersDao(): DisordersDao
    abstract fun fightingArtsDao(): FightingArtsDao
    abstract fun glossaryDao(): GlossaryDao

    object Factory {

        fun createDatabase(name: CharSequence, context: Context, initializer: Callback): KdmDatabase {
            return Room.databaseBuilder(
                    context.applicationContext,
                    KdmDatabase::class.java,
                    name.toString()
            ).addCallback(initializer).build()
        }
    }

    abstract class Initializer(private val context: Context) : RoomDatabase.Callback() {

        companion object {
            val TAG: String = Initializer::class.java.name
        }

        /**
         * Gets the database that was just created.
         *
         * If this seems curiously circular, it's because the [onCreate] callback doesn't actually get invoked
         * with an instance of the database that was just created, so we don't have a readily-accessible way of
         * interacting with it (unless we wanted to use the provided [SupportSQLiteDatabase] directly, instead
         * of the nice DAOs that it creates for us? That seems contrary to the whole point of using Room).
         *
         * Instead, we rely on whoever is creating the database to provide a method to get it back again later, and
         * assume that the Room module will ensure that the database is actually created before calling [onCreate].
         *
         * This is less than ideal. It would be much better if [onCreate] had this as a parameter from the get-go.
         */
        abstract fun getDatabase(): KdmDatabase

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            Log.i(TAG, "Pre-populating DB")

            // TODO use a proper scope?
            GlobalScope.launch {
                with(getDatabase()) {
                    addExpansions(expansionsDao())
                    addDisorders(disordersDao())
                    addFightingArts(fightingArtsDao())
                    addGlossary(glossaryDao())
                }

                Log.i(TAG, "Done pre-populating DB")
            }
        }

        private suspend fun addExpansions(dao: ExpansionsDao) {

            Log.i(TAG, "Adding expansions")

            val json = getJsonArrayFromResource(R.raw.expansions)

            for (i in 0 until json.length()) {
                with(json.getJSONObject(i)) {
                    dao.insert(Expansion(name = getString("name"), isIncluded = true))
                }
            }
        }

        private suspend fun addDisorders(dao: DisordersDao) {

            Log.i(TAG, "Adding disorders")

            val json = getJsonArrayFromResource(R.raw.disorders)

            for (i in 0 until json.length()) {
                with(json.getJSONObject(i)) {
                    dao.insert(Disorder(
                            name = getString("name"),
                            description = getString("description"),
                            expansion = getString("expansion")))
                }
            }
        }

        private suspend fun addFightingArts(dao: FightingArtsDao) {

            Log.i(TAG, "Adding fighting arts")

            run {
                val json = getJsonArrayFromResource(R.raw.fighting_art_types)

                for (i in 0 until json.length()) {
                    with(json.getJSONObject(i)) {
                        dao.insert(FightingArtType(name = getString("name")))
                    }
                }
            }

            run {
                val json = getJsonArrayFromResource(R.raw.fighting_arts)

                for (i in 0 until json.length()) {
                    with(json.getJSONObject(i)) {
                        dao.insert(FightingArt(
                                name = getString("name"),
                                description = getString("description"),
                                expansion = getString("expansion"),
                                type = getString("type")))
                    }
                }
            }
        }

        private suspend fun addGlossary(dao: GlossaryDao) {

            Log.i(TAG, "Adding glossary")

            val json = getJsonArrayFromResource(R.raw.glossary)

            for (i in 0 until json.length()) {
                with(json.getJSONObject(i)) {
                    dao.insert(GlossaryEntry(
                            name = getString("name"),
                            description = getString("description")))
                }
            }
        }

        private fun getJsonArrayFromResource(@RawRes keywordsFile: Int): JSONArray {
            return JSONArray(context.resources.openRawResource(keywordsFile).bufferedReader().use { it.readText() })
        }
    }
}