package ca.meshytama.kotlindeathmonster.expansions

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import ca.meshytama.kotlindeathmonster.KdmApplication
import ca.meshytama.kotlindeathmonster.model.KdmDatabase

/**
 * An activity to show a checkbox list of all the expansions, in order to specify
 * which expansions are part of the campaign (and thus filter out content from any
 * expansions which are not).
 */
class ExpansionsActivity : AppCompatActivity() {

    private lateinit var model: KdmDatabase
    private lateinit var view: ExpansionsView
    private lateinit var presenter: ExpansionsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        view = AndroidExpansionsView(this)
        setContentView(view as View)

        model = (application as KdmApplication).database

        presenter = ExpansionsPresenterImpl(model.expansionsDao(), view)
        presenter.start()
    }
}