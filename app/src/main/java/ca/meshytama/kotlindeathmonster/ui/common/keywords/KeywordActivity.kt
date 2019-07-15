package ca.meshytama.kotlindeathmonster.ui.common.keywords

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ca.meshytama.kotlindeathmonster.KdmApplication
import ca.meshytama.kotlindeathmonster.model.KdmDatabase

/**
 * Base activity class for a page that shows a searchable list of keywords.
 *
 * Note: This activity must be launched from within a [KdmApplication] instance!
 */
abstract class KeywordActivity : AppCompatActivity() {

    private lateinit var model: KdmDatabase
    private lateinit var view: KeywordView
    private lateinit var presenter: KeywordPresenter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        view = AndroidKeywordView(this)
        setContentView(view as View)

        model = (application as KdmApplication).database

        presenter = createPresenter(model, view)
        presenter.start()
    }

    abstract fun createPresenter(database: KdmDatabase, view: KeywordView): KeywordPresenter
}
