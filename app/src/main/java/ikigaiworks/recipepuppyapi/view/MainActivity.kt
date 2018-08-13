package ikigaiworks.recipepuppyapi.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.View
import ikigaiworks.recipepuppyapi.R
import kotlinx.android.synthetic.main.activity_main.*
import android.support.v4.view.MenuItemCompat.getActionView
import android.support.v4.view.MenuItemCompat.getActionView
import android.support.v7.widget.SearchView
import android.widget.Toast
import ikigaiworks.recipepuppyapi.utils.onQueryTextChangeListener
import kotlinx.android.synthetic.main.loader.*


class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    var onQueryTextChangeListener: onQueryTextChangeListener? = null


    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        onQueryTextChangeListener?.onSearchQuery(newText)
        return true
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(search_toolbar)
        launchList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.toolbarmenu, menu)
        val mSearch = menu?.findItem(R.id.action_search)
        val mSearchView = mSearch?.getActionView() as SearchView
        mSearchView.queryHint = getString(R.string.search_hint)
        mSearchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    private fun transactionSubModule(fragment: Fragment, backStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_container, fragment, fragment.tag)
        if (backStack) {
            transaction.addToBackStack(fragment.tag)
        }
        transaction.commit()
    }

    fun launchList() {
        val listFragment = ListFragment.newInstance()
        transactionSubModule(listFragment, false)
    }

    fun showLoader() {
        loading_progress_bar_transparent?.visibility = View.VISIBLE
    }

    fun hideLoader(){
        loading_progress_bar_transparent?.visibility = View.GONE
    }

}
