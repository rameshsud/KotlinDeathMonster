package ca.meshytama.kotlindeathmonster.keywords

/**
 * A keyword, combined with the information needed to display it.
 * @property keyword The keyword to display.
 * @property group The group to which the keyword belongs.
 * @property isFirstInGroup Whether or not this keyword is the first one in its group.
 */
data class DisplayableKeyword (val keyword: Keyword, val group: Char, val isFirstInGroup: Boolean)