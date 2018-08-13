package ikigaiworks.recipepuppyapi.view

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ikigaiworks.recipepuppyapi.R
import ikigaiworks.recipepuppyapi.viewmodel.RecipePuppyListViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import ikigaiworks.recipepuppyapi.api.model.Response
import ikigaiworks.recipepuppyapi.api.model.ResultsItem
import ikigaiworks.recipepuppyapi.utils.onQueryTextChangeListener
import ikigaiworks.recipepuppyapi.view.adapter.RecipePuppyAdapter
import kotlinx.android.synthetic.main.fragment_list.*


class ListFragment : Fragment(), LifecycleOwner,View.OnClickListener,onQueryTextChangeListener {



    override fun onSearchQuery(text: String?) {
        text?.let { viewModel?.getRecipeList(it) }
        (activity as MainActivity).showLoader()
    }


    var recipes: List<ResultsItem?> = ArrayList<ResultsItem>()

    override fun onClick(view: View?) {
        val itemPosition = recipe_list?.getChildLayoutPosition(view)
        val item = itemPosition?.let { recipes?.get(it) }

        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("recipe", item)
        startActivity(intent)

    }


    var viewModel : RecipePuppyListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RecipePuppyListViewModel::class.java)
        (activity as MainActivity).onQueryTextChangeListener = this

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeViewModel(viewModel)
    }
    override fun onResume() {
        super.onResume()
        val condition = recipes?.size==0
        isEmptyList(condition)
        recipe_list?.layoutManager=GridLayoutManager(activity,1)
        recipe_list?.adapter = RecipePuppyAdapter(recipes, context,this)
    }

    private fun isEmptyList(condition: Boolean){
        if(condition){
            recipe_list.visibility = View.GONE
            empty_text.visibility = View.VISIBLE
        }else{
            recipe_list.visibility = View.VISIBLE
            empty_text.visibility = View.GONE
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    private fun observeViewModel(viewModel: RecipePuppyListViewModel?){
        viewModel?.getRecipeListObservable()?.observe(this,object : Observer<Response>{
            override fun onChanged(t: Response?) {
                if(activity!=null) {
                    updateData(t)
                }
            }

        })
    }

    private fun updateData(response: Response?){
        if(response?.results!=null) {
            recipes = response?.results!!
        }
        (recipe_list?.adapter as RecipePuppyAdapter).items = response?.results
        (recipe_list?.adapter as RecipePuppyAdapter).notifyDataSetChanged()
        isEmptyList(response?.results?.size!! == 0)
        (activity as MainActivity).hideLoader()
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ListFragment.
         */
        @JvmStatic
            fun newInstance(): ListFragment {
                return ListFragment()
            }
    }
}
