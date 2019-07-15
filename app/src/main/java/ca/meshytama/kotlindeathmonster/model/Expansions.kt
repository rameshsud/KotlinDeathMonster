package ca.meshytama.kotlindeathmonster.model

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query

@Entity(tableName = "expansions")
data class Expansion(
        @PrimaryKey val name: String,
        @ColumnInfo(defaultValue = "1") val isIncluded: Boolean
)

@Dao
interface ExpansionsDao {
    @Query("SELECT * from expansions")
    suspend fun getAll(): List<Expansion>

    @Insert
    suspend fun insert(expansion: Expansion)

    // Including / excluding by a String instead of an Expansion argument feels wrong,
    // but there doesn't seem to be any nice way of passing that in here.
    // a) We can't dereference the name out of the expansion in the @Query, there's no syntax for it.
    // b) We can't use default Expansion-taking methods that call an internal String-taking method,
    //    or Room complains about the default methods missing a DAO annotation.

    @Query("UPDATE expansions SET isIncluded = 1 where name = :name")
    suspend fun include(name: String)

    @Query("UPDATE expansions SET isIncluded = 0 where name = :name")
    suspend fun exclude(name: String)
}