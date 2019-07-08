package ca.meshytama.kotlindeathmonster.keywords

/**
 * View interface for the keyword pages.
 */
interface KeywordView {

    /**
     * Attaches a presenter to the view.
     * The view is now responsible for forwarding [KeywordPresenter] events to the given presenter.
     */
    fun attachPresenter(presenter: KeywordPresenter)

    /**
     * Displays the given list of keywords.
     */
    fun displayKeywords(sortedKeywords: List<DisplayableKeyword>)

}