package ikigaiworks.recipepuppyapi.view

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import ikigaiworks.recipepuppyapi.R
import ikigaiworks.recipepuppyapi.api.model.ResultsItem
import kotlinx.android.synthetic.main.fragment_detail.*

private const val ARG_RECIPE = "recipe"


class DetailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var recipe: ResultsItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            recipe = it.getParcelable(ARG_RECIPE)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onResume() {
        super.onResume()
        title.text = recipe?.title
        ingredients.text = recipe?.ingredients
        web_reference.text = recipe?.href
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 item from recipe.
         * @return A new instance of fragment DetailFragment.
         */
        @JvmStatic
        fun newInstance(param1: ResultsItem?) =
                DetailFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(ARG_RECIPE, param1)
                    }
                }
    }
}
