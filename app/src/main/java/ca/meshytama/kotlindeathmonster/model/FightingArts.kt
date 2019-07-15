package ca.meshytama.kotlindeathmonster.model

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(tableName = "fighting_arts",
        foreignKeys = [ForeignKey(
                entity = Expansion::class,
                parentColumns = ["name"],
                childColumns = ["expansion"])],
        indices = [Index("expansion")]
)
data class FightingArt(
        @PrimaryKey val name: String,
        val description: String,
        val expansion: String
)

@Dao
interface FightingArtsDao {
    @Query("SELECT fighting_arts.* from fighting_arts join expansions on fighting_arts.expansion = expansions.name where isIncluded")
    suspend fun getAll(): List<FightingArt>

    @Query("SELECT fighting_arts.* from fighting_arts join expansions on fighting_arts.expansion = expansions.name where isIncluded AND fighting_arts.name like '%' || :name || '%'")
    suspend fun getAllMatchingName(name: String): List<FightingArt>

    @Query("SELECT fighting_arts.* from fighting_arts join expansions on fighting_arts.expansion = expansions.name where isIncluded ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandom(): FightingArt

    @Query("SELECT fighting_arts.* from fighting_arts join expansions on fighting_arts.expansion = expansions.name where isIncluded ORDER BY RANDOM() LIMIT 3")
    suspend fun get3Random(): List<FightingArt>

    @Insert
    suspend fun insert(fightingArt: FightingArt)
}