package ca.meshytama.kotlindeathmonster.model

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(tableName = "expansions")
data class Expansion(
        @PrimaryKey val name: String
)

@Dao
interface ExpansionsDao {
    @Query("SELECT * from expansions")
    suspend fun getAll(): List<Expansion>

    @Insert
    suspend fun insert(expansion: Expansion)
}