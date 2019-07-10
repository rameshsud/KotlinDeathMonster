package ca.meshytama.kotlindeathmonster.keywords

/**
 * A keyword.
 * @property name The name of keyword.
 * @property description The description of the keyword.
 */
data class Keyword(val name: String, val description: String)

/**
 * A keyword, combined with the information needed to display it.
 * @property keyword The keyword to display.
 * @property group The group to which the keyword belongs.
 *                 For the time being, this is just a single character (i.e. the initial of the name)
 * @property isFirstInGroup Whether or not this keyword is the first one in its group.
 */
data class DisplayableKeyword(val keyword: Keyword, val group: Char, val isFirstInGroup: Boolean)