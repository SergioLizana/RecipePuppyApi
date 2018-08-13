package ikigaiworks.recipepuppyapi.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import ikigaiworks.recipepuppyapi.R
import ikigaiworks.recipepuppyapi.api.model.ResultsItem
import ikigaiworks.recipepuppyapi.view.viewholder.RecipePuppyViewHolder

class RecipePuppyAdapter(var items: List<ResultsItem?>?, val context: Context?, val listener:View.OnClickListener) : RecyclerView.Adapter<RecipePuppyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipePuppyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recipe_row, parent, false)
        view.setOnClickListener(listener)
        return RecipePuppyViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipePuppyViewHolder, position: Int) {
        holder?.title?.text = items?.get(position)?.title
        holder?.ingredients?.text = items?.get(position)?.ingredients
        holder?.url?.text = items?.get(position)?.href
        Glide.with(context).load(items?.get(position)?.thumbnail).into(holder?.image)
    }

    override fun getItemCount(): Int {
        return items!!.size
    }

}

