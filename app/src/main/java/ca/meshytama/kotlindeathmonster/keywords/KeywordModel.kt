package ca.meshytama.kotlindeathmonster.keywords

/**
 * Model interface for the keyword-style pages.
 */
interface KeywordModel {

    /**
     * Gets all the keywords the page can display.
     */
    fun getAllKeywords(): Collection<Keyword>

}