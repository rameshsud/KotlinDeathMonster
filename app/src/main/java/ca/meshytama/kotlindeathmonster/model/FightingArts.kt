package ca.meshytama.kotlindeathmonster.model

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(tableName = "fighting_art_types")
data class FightingArtType(
        @PrimaryKey val name: String
)

@Entity(tableName = "fighting_arts",
        foreignKeys = [
            ForeignKey(
                    entity = Expansion::class,
                    parentColumns = ["name"],
                    childColumns = ["expansion"]),
            ForeignKey(
                    entity = FightingArtType::class,
                    parentColumns = ["name"],
                    childColumns = ["type"])],
        indices = [Index("expansion"), Index("type")]
)
data class FightingArt(
        @PrimaryKey val name: String,
        val description: String,
        val expansion: String,
        val type: String
)

@Dao
interface FightingArtsDao {
    @Query("SELECT fighting_arts.* from fighting_arts join expansions on fighting_arts.expansion = expansions.name where isIncluded")
    suspend fun getIncluded(): List<FightingArt>

    @Query("SELECT fighting_arts.* from fighting_arts join expansions on fighting_arts.expansion = expansions.name where isIncluded AND fighting_arts.name like '%' || :name || '%'")
    suspend fun getIncludedMatchingName(name: String): List<FightingArt>

    @Query("SELECT fighting_arts.* from fighting_arts join expansions on fighting_arts.expansion = expansions.name join fighting_art_types on fighting_arts.type = fighting_art_types.name where isIncluded ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomIncluded(): FightingArt

    @Query("SELECT fighting_arts.* from fighting_arts join expansions on fighting_arts.expansion = expansions.name join fighting_art_types on fighting_arts.type = fighting_art_types.name where isIncluded ORDER BY RANDOM() LIMIT 3")
    suspend fun get3RandomIncluded(): List<FightingArt>

    @Insert
    suspend fun insert(fightingArt: FightingArt)

    @Insert
    suspend fun insert(fightingArtType: FightingArtType)
}