package ca.meshytama.kotlindeathmonster.model

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(tableName="glossary")
data class GlossaryEntry(
        @PrimaryKey val name: String,
        val description: String
)

@Dao
interface GlossaryDao {
    @Query("SELECT * from glossary")
    suspend fun getAll(): List<GlossaryEntry>

    @Insert
    suspend fun insert(glossaryEntry: GlossaryEntry)
}