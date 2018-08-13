package ikigaiworks.recipepuppyapi.view.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.recipe_row.view.*

class RecipePuppyViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val title = view.title
    val ingredients = view.ingredients
    val url = view.url
    val image = view.image



}

