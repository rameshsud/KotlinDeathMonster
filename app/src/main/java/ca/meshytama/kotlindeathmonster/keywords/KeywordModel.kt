package ca.meshytama.kotlindeathmonster.keywords

import ca.meshytama.kotlindeathmonster.model.KdmDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Model interface for the keyword pages.
 */
interface KeywordModel {

    /**
     * Gets all the keywords the page can display.
     */
    suspend fun getAllKeywords(): Collection<Keyword>
}

class GlossaryModel(private val database:KdmDatabase):KeywordModel {

    override suspend fun getAllKeywords() = withContext(Dispatchers.IO){
        database.glossaryDao().getAll().map {
            Keyword(it.name, it.description)
        }
    }
}

class FightingArtsModel(private val database:KdmDatabase):KeywordModel {

    override suspend fun getAllKeywords() = withContext(Dispatchers.IO){
        database.fightingArtsDao().getAll().map{
            Keyword(it.name, it.description)
        }
    }
}

class DisordersModel(private val database:KdmDatabase):KeywordModel{

    override suspend fun getAllKeywords() = withContext(Dispatchers.IO){
        database.disordersDao().getAll().map {
            Keyword(it.name, it.description)
        }
    }
}