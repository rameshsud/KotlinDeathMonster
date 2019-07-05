package ca.meshytama.kotlindeathmonster.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ca.meshytama.kotlindeathmonster.R
import ca.meshytama.kotlindeathmonster.keywords.DisordersPage
import ca.meshytama.kotlindeathmonster.keywords.FightingArtsPage
import ca.meshytama.kotlindeathmonster.keywords.GlossaryPage
import kotlinx.android.synthetic.main.activity_home_page.disordersButton
import kotlinx.android.synthetic.main.activity_home_page.fightingArtsButton
import kotlinx.android.synthetic.main.activity_home_page.glossaryButton
import kotlinx.android.synthetic.main.activity_home_page.toolbar

/**
 * Launcher page for all the other more interesting activities.
 */
class HomePage : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // In order to avoid the blank page while launching an application, we specified a splash
        // theme for this activity in the manifest (since this is the launch activity).
        // We then apply the desired theme here, since by onCreate() we know we're ready to go.
        setTheme(R.style.AppTheme_NoActionBar)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        setSupportActionBar(toolbar)

        fightingArtsButton.setOnClickListener {
            startActivity(Intent(this, FightingArtsPage::class.java))
        }

        disordersButton.setOnClickListener {
            startActivity(Intent(this, DisordersPage::class.java))
        }

        glossaryButton.setOnClickListener {
            startActivity(Intent(this, GlossaryPage::class.java))
        }
    }

}
