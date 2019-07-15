package ca.meshytama.kotlindeathmonster.ui.glossary

import ca.meshytama.kotlindeathmonster.model.KdmDatabase
import ca.meshytama.kotlindeathmonster.ui.common.keywords.KeywordActivity
import ca.meshytama.kotlindeathmonster.ui.common.keywords.KeywordPresenter
import ca.meshytama.kotlindeathmonster.ui.common.keywords.KeywordView

/**
 * The activity that shows the glossary.
 */
class GlossaryActivity : KeywordActivity() {
    override fun createPresenter(database: KdmDatabase, view: KeywordView): KeywordPresenter {
        return GlossaryPresenter(database.glossaryDao(), view)
    }
}