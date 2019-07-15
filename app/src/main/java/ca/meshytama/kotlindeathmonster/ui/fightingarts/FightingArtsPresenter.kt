package ca.meshytama.kotlindeathmonster.ui.fightingarts

import ca.meshytama.kotlindeathmonster.model.FightingArt
import ca.meshytama.kotlindeathmonster.model.FightingArtsDao
import ca.meshytama.kotlindeathmonster.ui.common.keywords.BaseKeywordPresenter
import ca.meshytama.kotlindeathmonster.ui.common.keywords.Keyword
import ca.meshytama.kotlindeathmonster.ui.common.keywords.KeywordView

/**
 * Presenter implementation for the Fighting Arts page.
 */
class FightingArtsPresenter(private val dao: FightingArtsDao, view: KeywordView) : BaseKeywordPresenter<FightingArt>(view) {
    override suspend fun getAllContainingName(name: CharSequence) = dao.getIncludedMatchingName(name.toString())
    override suspend fun getAll() = dao.getIncluded()
    override fun toKeyword(input: FightingArt) = Keyword(input.name, input.description)
}