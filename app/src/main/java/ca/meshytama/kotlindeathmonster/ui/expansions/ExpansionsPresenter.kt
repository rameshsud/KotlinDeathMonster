package ca.meshytama.kotlindeathmonster.ui.expansions

import ca.meshytama.kotlindeathmonster.model.Expansion
import ca.meshytama.kotlindeathmonster.model.ExpansionsDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Presenter interface for the expansions page.
 */
interface ExpansionsPresenter {

    /**
     * Starts the presenter.
     */
    fun start()

    /**
     * Includes an expansion in the campaign.
     */
    fun includeExpansion(expansion: Expansion)

    /**
     * Excludes an expansion from the campaign.
     */
    fun excludeExpansion(expansion: Expansion)

}

class ExpansionsPresenterImpl(private val dao: ExpansionsDao, private val view: ExpansionsView)
    : ExpansionsPresenter, CoroutineScope by MainScope() {

    override fun start() {
        view.attachPresenter(this)

        launch {
            view.displayExpansions(withContext(Dispatchers.IO) { dao.getAll() })
        }
    }

    override fun includeExpansion(expansion: Expansion) {
        launch(Dispatchers.IO) { dao.include(expansion.name) }
    }

    override fun excludeExpansion(expansion: Expansion) {
        launch(Dispatchers.IO) { dao.exclude(expansion.name) }
    }
}