package ca.meshytama.kotlindeathmonster.model

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(tableName = "disorders",
        foreignKeys = [ForeignKey(
                entity = Expansion::class,
                parentColumns = ["name"],
                childColumns = ["expansion"])],
        indices = [Index("expansion")]
)
data class Disorder(
        @PrimaryKey val name: String,
        val description: String,
        val expansion: String
)

@Dao
interface DisordersDao {
    @Query("SELECT disorders.* from disorders join expansions on disorders.expansion = expansions.name where isIncluded")
    suspend fun getAll(): List<Disorder>

    @Query("SELECT disorders.* from disorders join expansions on disorders.expansion = expansions.name where isIncluded AND disorders.name like '%' || :name || '%'")
    suspend fun getAllMatchingName(name: String): List<Disorder>

    @Query("SELECT disorders.* from disorders join expansions on disorders.expansion = expansions.name where isIncluded ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandom(): Disorder

    @Insert
    suspend fun insert(disorder: Disorder)
}