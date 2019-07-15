package ca.meshytama.kotlindeathmonster.ui.disorders

import ca.meshytama.kotlindeathmonster.model.Disorder
import ca.meshytama.kotlindeathmonster.model.DisordersDao
import ca.meshytama.kotlindeathmonster.ui.common.keywords.BaseKeywordPresenter
import ca.meshytama.kotlindeathmonster.ui.common.keywords.Keyword
import ca.meshytama.kotlindeathmonster.ui.common.keywords.KeywordView

/**
 * Presenter implementation for the Disorders page.
 */
class DisordersPresenter(private val dao: DisordersDao, view: KeywordView) : BaseKeywordPresenter<Disorder>(view) {
    override suspend fun getAllContainingName(name: CharSequence) = dao.getIncludedMatchingName(name.toString())
    override suspend fun getAll() = dao.getIncluded()
    override fun toKeyword(input: Disorder) = Keyword(input.name, input.description)
}