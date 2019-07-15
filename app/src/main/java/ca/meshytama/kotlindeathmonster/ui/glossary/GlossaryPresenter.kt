package ca.meshytama.kotlindeathmonster.ui.glossary

import ca.meshytama.kotlindeathmonster.model.GlossaryDao
import ca.meshytama.kotlindeathmonster.model.GlossaryEntry
import ca.meshytama.kotlindeathmonster.ui.common.keywords.BaseKeywordPresenter
import ca.meshytama.kotlindeathmonster.ui.common.keywords.Keyword
import ca.meshytama.kotlindeathmonster.ui.common.keywords.KeywordView

/**
 * Presenter implementation for the Glossary page.
 */
class GlossaryPresenter(private val dao: GlossaryDao, view: KeywordView) : BaseKeywordPresenter<GlossaryEntry>(view) {
    override suspend fun getAllContainingName(name: CharSequence) = dao.getAllMatchingName(name.toString())
    override suspend fun getAll() = dao.getAll()
    override fun toKeyword(input: GlossaryEntry) = Keyword(input.name, input.description)
}