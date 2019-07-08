package ca.meshytama.kotlindeathmonster.keywords

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import ca.meshytama.kotlindeathmonster.R
import kotlinx.android.synthetic.main.activity_keywords.view.keywordsListView
import kotlinx.android.synthetic.main.activity_keywords.view.searchBar
import kotlinx.android.synthetic.main.keyword_list_entry.view.firstLine
import kotlinx.android.synthetic.main.keyword_list_entry.view.groupIcon
import kotlinx.android.synthetic.main.keyword_list_entry.view.secondLine

/**
 * Android implementation of the [KeywordView] interface.
 */
class AndroidKeywordView(context: Context) : LinearLayout(context), KeywordView {

    /**
     * TODO
     */
    lateinit var presenter: KeywordPresenter

    override fun attachPresenter(presenter: KeywordPresenter) {
        this.presenter = presenter
    }

    init {
        inflate(context, R.layout.activity_keywords, this)
    }

    private val keywordsListViewAdapter = object :
            ArrayAdapter<DisplayableKeyword>(
                    context,
                    R.layout.keyword_list_entry,
                    ArrayList<DisplayableKeyword>()
            ) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            val myView = convertView ?: LayoutInflater.from(context).inflate(
                    R.layout.keyword_list_entry,
                    parent,
                    false
            )
            val item = getItem(position)!!
            with(myView){
                firstLine.text = item.keyword.name
                secondLine.text = item.keyword.description
                groupIcon.visibility = if (item.isFirstInGroup) VISIBLE else INVISIBLE
                groupIcon.text = item.group.toString()
            }
            return myView
        }
    }

    init {
        keywordsListView.adapter = keywordsListViewAdapter
    }

    private val textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            // don't care
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // also don't care
        }

        override fun afterTextChanged(s: Editable?) {
            presenter.queryChanged(s)
        }
    }

    init {
        searchBar.addTextChangedListener(textWatcher)
    }

    override fun displayKeywords(sortedKeywords: List<DisplayableKeyword>) {
        with(keywordsListViewAdapter) {
            setNotifyOnChange(false)
            clear()
            addAll(sortedKeywords)
            notifyDataSetChanged()
        }
    }

}