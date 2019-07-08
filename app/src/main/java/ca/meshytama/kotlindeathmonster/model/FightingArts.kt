package ca.meshytama.kotlindeathmonster.model

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(tableName = "fighting_arts",
        foreignKeys = [ForeignKey(
                entity = Expansion::class,
                parentColumns = ["name"],
                childColumns = ["expansion"])]
)
data class FightingArt(
        @PrimaryKey val name: String,
        val description: String,
        val expansion: String
)

@Dao
interface FightingArtsDao {
    @Query("SELECT * from fighting_arts")
    suspend fun getAll(): List<FightingArt>

    @Query("SELECT * from fighting_arts ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandom(): FightingArt

    @Query("SELECT * from fighting_arts ORDER BY RANDOM() LIMIT 3")
    suspend fun get3Random(): List<FightingArt>

    @Insert
    suspend fun insert(fightingArt: FightingArt)
}