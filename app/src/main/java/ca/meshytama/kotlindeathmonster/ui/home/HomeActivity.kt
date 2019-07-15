package ca.meshytama.kotlindeathmonster.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ca.meshytama.kotlindeathmonster.R
import ca.meshytama.kotlindeathmonster.ui.expansions.ExpansionsActivity
import ca.meshytama.kotlindeathmonster.ui.common.keywords.DisordersActivity
import ca.meshytama.kotlindeathmonster.ui.common.keywords.FightingArtsActivity
import ca.meshytama.kotlindeathmonster.ui.common.keywords.GlossaryActivity
import kotlinx.android.synthetic.main.activity_home.disordersButton
import kotlinx.android.synthetic.main.activity_home.expansionsButton
import kotlinx.android.synthetic.main.activity_home.fightingArtsButton
import kotlinx.android.synthetic.main.activity_home.glossaryButton
import kotlinx.android.synthetic.main.activity_home.toolbar

/**
 * Launcher activity for all the other more interesting activities.
 */
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // In order to avoid the blank page while launching an application, we specified a splash
        // theme for this activity in the manifest (since this is the launch activity).
        // We then apply the desired theme here, since by onCreate() we know we're ready to go.
        setTheme(R.style.AppTheme_NoActionBar)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)

        fightingArtsButton.setOnClickListener {
            startActivity(Intent(this, FightingArtsActivity::class.java))
        }

        disordersButton.setOnClickListener {
            startActivity(Intent(this, DisordersActivity::class.java))
        }

        glossaryButton.setOnClickListener {
            startActivity(Intent(this, GlossaryActivity::class.java))
        }

        expansionsButton.setOnClickListener {
            startActivity(Intent(this, ExpansionsActivity::class.java))
        }
    }

}
