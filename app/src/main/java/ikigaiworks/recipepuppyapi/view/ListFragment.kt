package ikigaiworks.recipepuppyapi.view

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ikigaiworks.recipepuppyapi.R
import ikigaiworks.recipepuppyapi.viewmodel.RecipePuppyListViewModel
import android.arch.lifecycle.ViewModelProviders
import android.widget.Toast
import ikigaiworks.recipepuppyapi.api.model.Response


class ListFragment : Fragment(), LifecycleOwner {


    var viewModel : RecipePuppyListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RecipePuppyListViewModel::class.java)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeViewModel(viewModel)
    }
    override fun onResume() {
        super.onResume()
        viewModel?.getRecipeList("q")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    private fun observeViewModel(viewModel: RecipePuppyListViewModel?){
        viewModel?.getRecipeListObservable()?.observe(this,object : Observer<Response>{
            override fun onChanged(t: Response?) {
                    Toast.makeText(activity,"Ha llegado el observador",Toast.LENGTH_LONG).show()
            }

        })
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
