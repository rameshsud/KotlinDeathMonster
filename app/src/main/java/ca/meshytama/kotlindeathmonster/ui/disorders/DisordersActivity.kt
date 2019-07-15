package ca.meshytama.kotlindeathmonster.ui.disorders

import ca.meshytama.kotlindeathmonster.model.KdmDatabase
import ca.meshytama.kotlindeathmonster.ui.common.keywords.KeywordActivity
import ca.meshytama.kotlindeathmonster.ui.common.keywords.KeywordPresenter
import ca.meshytama.kotlindeathmonster.ui.common.keywords.KeywordView

/**
 * The activity that shows the cards in the Disorders deck that are currently included in the campaign.
 */
class DisordersActivity : KeywordActivity() {
    override fun createPresenter(database: KdmDatabase, view: KeywordView): KeywordPresenter {
        return DisordersPresenter(database.disordersDao(), view)
    }
}