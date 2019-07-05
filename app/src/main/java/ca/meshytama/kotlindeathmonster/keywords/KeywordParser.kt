package ca.meshytama.kotlindeathmonster.keywords

import org.json.JSONArray

object KeywordParser {

    fun parseJsonArray(json: JSONArray): Collection<Keyword> {
        val keywords: MutableCollection<Keyword> = ArrayList()
        for (i in 0 until json.length()) {
            with(json.getJSONObject(i)) {
                keywords.add(Keyword(getString("name"), getString("description")))
            }
        }
        return keywords
    }

}