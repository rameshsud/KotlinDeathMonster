package ca.meshytama.kotlindeathmonster.keywords

import java.util.Objects

/**
 * Presenter implementation for keyword-style pages.
 */
class KeywordPresenterImpl(private val model: KeywordModel, private val view: KeywordView) : KeywordPresenter {

    override fun queryChanged(newQuery: CharSequence?) {
        view.displayKeywords(formatKeywordsForDisplay(getMatchingKeywordsFromModel(newQuery)))
    }

    override fun start() {
        view.attachPresenter(this)
        queryChanged(null)
    }

    /**
     * Gets all the [Keyword]s matching the given pattern from the [model].
     */
    private fun getMatchingKeywordsFromModel(pattern: CharSequence?): Collection<Keyword> {
        return when {
            pattern == null || pattern.isEmpty() -> model.getAllKeywords()
            else -> model.getAllKeywords().filter { it.name.contains(pattern, true) }
        }
    }

    /**
     * Formats the given [Keyword]s as [DisplayableKeyword]s and returns them in a list, ordered by their initial.
     * See [getInitial].
     */
    private fun formatKeywordsForDisplay(keywords: Collection<Keyword>) : List<DisplayableKeyword> {
        val sortedKeywords = ArrayList<DisplayableKeyword>()
        var lastSeenInitial: Char? = null
        for (keyword in keywords.sortedBy { it.name.toUpperCase() }) {
            val initial = getInitial(keyword.name)
            val isFirst = !Objects.equals(initial, lastSeenInitial)
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