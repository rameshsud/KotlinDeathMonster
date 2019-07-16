package ca.meshytama.kotlindeathmonster.ui.expansions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import ca.meshytama.kotlindeathmonster.R.layout
import ca.meshytama.kotlindeathmonster.model.Expansion
import kotlinx.android.synthetic.main.activity_expansions.view.expansionsListView
import kotlinx.android.synthetic.main.checkbox_list_entry.view.checkBox
import kotlinx.android.synthetic.main.checkbox_list_entry.view.text

/**
 * View interface for the expansions page.
 */
interface ExpansionsView {

    /**
     * Attaches a presenter to the view.
     * The view is now responsible for forwarding [ExpansionsPresenter] events to the given presenter.
     */
    fun attachPresenter(presenter: ExpansionsPresenter)

    /**
     * Displays the given list of expansions.
     */
    fun displayExpansions(expansions: Collection<Expansion>)
}

class AndroidExpansionsView(context: Context) : LinearLayout(context), ExpansionsView {

    private var presenter: ExpansionsPresenter? = null

    override fun attachPresenter(presenter: ExpansionsPresenter) {
        this.presenter = presenter
    }

    init {
        inflate(context, layout.activity_expansions, this)
    }

    private val expansionsListViewAdapter = object :
            ArrayAdapter<Expansion>(
                    context,
                    layout.checkbox_list_entry,
                    ArrayList<Expansion>()
            ) {
        val inflater = LayoutInflater.from(context)

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            val myView = convertView ?: inflater.inflate(
                    layout.checkbox_list_entry,
                    parent,
                    false
            )
            val item = getItem(position)!!
            with(myView) {
                text.text = item.name
                checkBox.isChecked = item.isIncluded

                // It is important to use an OnClickListener instead of an OnCheckedChangeListener,
                // or else this callback gets fired at undesired times (i.e. during the creation of the view),
                // which leads to very odd behaviour.
                checkBox.setOnClickListener {
                    if (checkBox.isChecked) presenter?.includeExpansion(item)
                    else presenter?.excludeExpansion(item)
                }
            }
            return myView
        }
    }

    init {
        expansionsListView.adapter = expansionsListViewAdapter
    }

    override fun displayExpansions(expansions: Collection<Expansion>) {
        with(expansionsListViewAdapter) {
            setNotifyOnChange(false)
            clear()
            addAll(expansions)
            notifyDataSetChanged()
        }
    }
}