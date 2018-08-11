package ikigaiworks.recipepuppyapi.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import ikigaiworks.recipepuppyapi.api.model.Response
import ikigaiworks.recipepuppyapi.api.rest.RecipePuppyRepository


class RecipePuppyListViewModel(application: Application) : AndroidViewModel(application) {
    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    var projectListObservable: LiveData<Response>? = null

    fun getRecipeList(query: String){
        projectListObservable = RecipePuppyRepository.newInstance().getRecipeList(query)
    }

    fun getRecipeListObservable(): LiveData<Response>? {
        return projectListObservable
    }
}