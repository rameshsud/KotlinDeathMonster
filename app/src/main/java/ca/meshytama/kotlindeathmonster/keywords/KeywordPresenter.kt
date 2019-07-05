package ca.meshytama.kotlindeathmonster.keywords

/**
 * Presenter interface for keyword-style pages.
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