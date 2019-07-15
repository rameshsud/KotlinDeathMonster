package ca.meshytama.kotlindeathmonster.ui.fightingarts

import ca.meshytama.kotlindeathmonster.model.KdmDatabase
import ca.meshytama.kotlindeathmonster.ui.common.keywords.KeywordActivity
import ca.meshytama.kotlindeathmonster.ui.common.keywords.KeywordPresenter
import ca.meshytama.kotlindeathmonster.ui.common.keywords.KeywordView

/**
 * The activity that shows the cards in the Fighting Arts deck that are currently included in the campaign.
 */
class FightingArtsActivity : KeywordActivity() {
    override fun createPresenter(database: KdmDatabase, view: KeywordView): KeywordPresenter {
        return FightingArtsPresenter(database.fightingArtsDao(), view)
    }
}