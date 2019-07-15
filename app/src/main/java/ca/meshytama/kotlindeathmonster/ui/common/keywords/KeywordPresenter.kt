package ca.meshytama.kotlindeathmonster.ui.common.keywords

import ca.meshytama.kotlindeathmonster.model.Disorder
import ca.meshytama.kotlindeathmonster.model.DisordersDao
import ca.meshytama.kotlindeathmonster.model.FightingArt
import ca.meshytama.kotlindeathmonster.model.FightingArtsDao
import ca.meshytama.kotlindeathmonster.model.GlossaryDao
import ca.meshytama.kotlindeathmonster.model.GlossaryEntry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Presenter interface for keyword pages.
 */
interface KeywordPresenter {

    /**
     * Starts the presenter.
     */
    fun start()

    /**
     * Must be called by the [KeywordView] whenever the search query changes.
     */
    fun onQueryChanged(newQuery: CharSequence?)

}

/**
 * Base presenter class for keyword pages.
 * Suitable for presenting a collection of any type [T] which can be converted into a [Keyword].
 */
abstract class BaseKeywordPresenter<T>(private val view: KeywordView) :
        KeywordPresenter, CoroutineScope by MainScope() {

    private var currentQuery: CharSequence? = null

    override fun onQueryChanged(newQuery: CharSequence?) {
        currentQuery = newQuery
        tellViewToDisplayKeywords()
    }

    override fun start() {
        view.attachPresenter(this)
        tellViewToDisplayKeywords()
    }

    private fun tellViewToDisplayKeywords() {
        launch {
            view.displayKeywords(formatKeywordsForDisplay(getMatchingKeywords(currentQuery)))
        }
    }

    private suspend fun getMatchingKeywords(pattern: CharSequence?) = withContext(Dispatchers.IO) {
        when {
            pattern == null || pattern.isEmpty() -> getAll()
            else -> getAllContainingName(pattern)
        }.map {
            toKeyword(it)
        }
    }

    /**
     * Gets all the [T]s from the model.
     */
    abstract suspend fun getAll(): Collection<T>

    /**
     * Gets all the [T]s from the model whose "name" (or similar) contains the given [name].
     */
    abstract suspend fun getAllContainingName(name: CharSequence): Collection<T>

    /**
     * Converts a [T] into a [Keyword].
     */
    abstract fun toKeyword(input: T): Keyword

    /**
     * Formats the given [Keyword]s as [DisplayableKeyword]s and returns them in a list, ordered by their name.
     */
    private fun formatKeywordsForDisplay(keywords: Collection<Keyword>): List<DisplayableKeyword> {
        val sortedKeywords = ArrayList<DisplayableKeyword>()
        var lastSeenInitial: Char? = null
        for (keyword in keywords.sortedBy { it.name.toUpperCase() }) {
            val initial = getInitial(keyword.name)
            val isFirst = initial != lastSeenInitial
            lastSeenInitial = initial
            sortedKeywords.add(DisplayableKeyword(keyword, initial, isFirst))
        }
        return sortedKeywords
    }

    private fun getInitial(input: CharSequence): Char {
        return when (val first = input.first().toUpperCase()) {
            in 'A'..'Z' -> first
            else -> '#'
        }
    }
}

/**
 * Presenter implementation for the Disorders page.
 */
class DisordersPresenter(private val dao: DisordersDao, view: KeywordView) : BaseKeywordPresenter<Disorder>(view) {
    override suspend fun getAllContainingName(name: CharSequence) = dao.getIncludedMatchingName(name.toString())
    override suspend fun getAll() = dao.getIncluded()
    override fun toKeyword(input: Disorder) = Keyword(input.name, input.description)
}

/**
 * Presenter implementation for the Fighting Arts page.
 */
class FightingArtsPresenter(private val dao: FightingArtsDao, view: KeywordView) : BaseKeywordPresenter<FightingArt>(view) {
    override suspend fun getAllContainingName(name: CharSequence) = dao.getIncludedMatchingName(name.toString())
    override suspend fun getAll() = dao.getIncluded()
    override fun toKeyword(input: FightingArt) = Keyword(input.name, input.description)
}

/**
 * Presenter implementation for the Glossary page.
 */
class GlossaryPresenter(private val dao: GlossaryDao, view: KeywordView) : BaseKeywordPresenter<GlossaryEntry>(view) {
    override suspend fun getAllContainingName(name: CharSequence) = dao.getAllMatchingName(name.toString())
    override suspend fun getAll() = dao.getAll()
    override fun toKeyword(input: GlossaryEntry) = Keyword(input.name, input.description)
}