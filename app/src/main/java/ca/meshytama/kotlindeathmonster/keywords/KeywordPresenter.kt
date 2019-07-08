package ca.meshytama.kotlindeathmonster.keywords

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/**
 * Presenter interface for keyword pages.
 */
interface KeywordPresenter {

    /**
     * Starts the presenter.
     * TODO is this really part of the presenter interface?
     */
    fun start()

    /**
     * Must be called by the [KeywordView] whenever the filter query changes.
     * @param newQuery The new filter query.
     */
    fun queryChanged(newQuery: CharSequence?)

}

/**
 * Presenter implementation for keyword pages.
 */
class KeywordPresenterImpl(private val model: KeywordModel, private val view: KeywordView):
        KeywordPresenter, CoroutineScope by MainScope() {

    private var currentQuery: CharSequence? = null

    override fun queryChanged(newQuery: CharSequence?) {
        currentQuery = newQuery
        tellViewToDisplayKeywords()
    }

    override fun start() {
        view.attachPresenter(this)
        tellViewToDisplayKeywords()
    }

    private fun tellViewToDisplayKeywords() {
        launch {
            view.displayKeywords(formatKeywordsForDisplay(getMatchingKeywordsFromModel(currentQuery)))
        }
    }

    /**
     * Gets all the [Keyword]s matching the given pattern from the [model].
     */
    private suspend fun getMatchingKeywordsFromModel(pattern: CharSequence?): Collection<Keyword> {
        return when {
            pattern == null || pattern.isEmpty() -> model.getAllKeywords()
            else -> model.getAllKeywords().filter { it.name.contains(pattern, true) }
        }
    }

    /**
     * Formats the given [Keyword]s as [DisplayableKeyword]s and returns them in a list, ordered by their name.
     * See [getInitial].
     */
    private fun formatKeywordsForDisplay(keywords: Collection<Keyword>): List<DisplayableKeyword> {
        val sortedKeywords = ArrayList<DisplayableKeyword>()
        var lastSeenInitial: Char? = null
        for (keyword in keywords.sortedBy { it.name.toUpperCase() }) {
            val initial = getInitial(keyword.name)
            val isFirst = initial == lastSeenInitial
            lastSeenInitial = initial
            sortedKeywords.add(DisplayableKeyword(keyword, initial, isFirst))
        }
        return sortedKeywords
    }

    /**
     * Gets the first initial of the given input.
     * @return An uppercase character between 'A' and 'Z' if the input starts with a letter, or '#' otherwise.
     */
    private fun getInitial(input: CharSequence): Char {
        return when (val first = input.first().toUpperCase()) {
            in 'A'..'Z' -> first
            else -> '#'
        }
    }
}