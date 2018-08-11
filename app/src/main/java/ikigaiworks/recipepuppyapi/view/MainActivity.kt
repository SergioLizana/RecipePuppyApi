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


class MainActivity : AppCompatActivity(),SearchView.OnQueryTextListener{
    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Toast.makeText(this,newText,Toast.LENGTH_LONG).show()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        launchList()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        getMenuInflater().inflate(R.menu.toolbarmenu, menu)
        val mSearch = menu?.findItem(R.id.action_search)
        val mSearchView = mSearch?.getActionView() as SearchView
        mSearchView.setQueryHint("Search")
        mSearchView.setOnQueryTextListener(this)
        return super.onCreateOptionsMenu(menu)
    }

    private fun transactionSubModule(fragment: Fragment,backStack: Boolean){
        val transaction = supportFragmentManager.beginTransaction()

        // Replace whatever is in the fragment_container view with this fragment,
        // and DO NOT add the transaction to the back stack since we don't want the user to
        // be able to navigate back
        transaction.add(R.id.fragment_container, fragment, fragment.tag)

        if(backStack) {
            transaction.addToBackStack(fragment.tag)
        }

        // Commit the transaction
        transaction.commit()
    }

    fun launchList(){
        val listFragment = ListFragment.newInstance()
        transactionSubModule(listFragment,false)
    }

    fun launchDetail(){

    }
}
