package ca.meshytama.kotlindeathmonster.keywords

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ca.meshytama.kotlindeathmonster.KdmApplication
import ca.meshytama.kotlindeathmonster.model.KdmDatabase

/**
 * Base activity class for a page that shows a filterable list of keywords.
 *
 * Note: This activity must be launched from within a [KdmApplication] instance!
 */
abstract class KeywordsActivity : AppCompatActivity() {

    private lateinit var model: KeywordModel
    private lateinit var view: KeywordView
    private lateinit var presenter: KeywordPresenter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        view = AndroidKeywordView(this)
        setContentView(view as View)

        model = createModel((application as KdmApplication).database)

        presenter = KeywordPresenterImpl(model, view)
        presenter.start()
    }

    abstract fun createModel(database: KdmDatabase):KeywordModel
}

/**
 * The activity that shows the Fighting Arts deck.
 */
class FightingArtsActivity : KeywordsActivity() {
    override fun createModel(database: KdmDatabase): KeywordModel {
        return FightingArtsModel(database)
    }
}

/**
 * The activity that shows the Disorder deck.
 */
class DisordersActivity : KeywordsActivity() {
    override fun createModel(database: KdmDatabase): KeywordModel {
        return DisordersModel(database)
    }
}

/**
 * The activity that shows the glossary.
 */
class GlossaryActivity : KeywordsActivity() {
    override fun createModel(database: KdmDatabase): KeywordModel {
        return GlossaryModel(database)
    }
}
