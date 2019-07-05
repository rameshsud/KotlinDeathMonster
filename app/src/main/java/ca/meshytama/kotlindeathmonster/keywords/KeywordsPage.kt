package ca.meshytama.kotlindeathmonster.keywords

import android.os.Bundle
import android.support.annotation.RawRes
import android.support.v7.app.AppCompatActivity
import android.view.View
import ca.meshytama.kotlindeathmonster.R
import org.json.JSONArray

/**
 * The page that shows the Fighting Arts deck.
 */
class FightingArtsPage : KeywordsPage(R.raw.fighting_arts)

/**
 * The page that shows the Disorder deck.
 */
class DisordersPage : KeywordsPage(R.raw.disorders)

/**
 * The page that shows the glossary.
 */
class GlossaryPage : KeywordsPage(R.raw.glossary)

/**
 * Base activity class for a page that shows a filterable list of keywords.
 * @param keywordsFile The resource file containing the keywords this page should show.
 */
abstract class KeywordsPage(@RawRes val keywordsFile: Int) : AppCompatActivity() {

    private lateinit var model: KeywordModel
    private lateinit var view: KeywordView
    private lateinit var presenter: KeywordPresenter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        view = KeywordViewImpl(this)
        setContentView(view as View)

        val keywordsJson = JSONArray(resources.openRawResource(keywordsFile).bufferedReader().use { it.readText() })
        model = KeywordModelImpl(keywordsJson)

        presenter = KeywordPresenterImpl(model, view)
        presenter.start()
    }
}
