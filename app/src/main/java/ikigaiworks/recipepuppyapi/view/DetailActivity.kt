package ikigaiworks.recipepuppyapi.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import android.widget.Toolbar
import com.bumptech.glide.Glide
import ikigaiworks.recipepuppyapi.R
import ikigaiworks.recipepuppyapi.api.model.ResultsItem
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.view.MenuItem


class DetailActivity : AppCompatActivity() {

    var recipe: ResultsItem? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recipe =  intent.getParcelableExtra<ResultsItem>("recipe")
        Glide.with(this).load(recipe?.thumbnail).into(image_crop)
        collapsing_toolbar.title = recipe?.title
        loadDetailFragment()

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadDetailFragment(){
        val detailFragment = DetailFragment.newInstance(recipe)
        transactionSubModule(detailFragment, false)
    }

    private fun transactionSubModule(fragment: Fragment, backStack: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()

        // Replace whatever is in the fragment_container view with this fragment,
        // and DO NOT add the transaction to the back stack since we don't want the user to
        // be able to navigate back
        transaction.add(R.id.fragment_container, fragment, fragment.tag)

        if (backStack) {
            transaction.addToBackStack(fragment.tag)
        }

        // Commit the transaction
        transaction.commit()
    }



}