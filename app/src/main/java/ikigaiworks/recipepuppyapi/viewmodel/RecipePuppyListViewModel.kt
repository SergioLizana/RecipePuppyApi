package ikigaiworks.recipepuppyapi.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import ikigaiworks.recipepuppyapi.api.model.Response
import ikigaiworks.recipepuppyapi.api.rest.RecipePuppyRepository


class RecipePuppyListViewModel(application: Application) : AndroidViewModel(application) {

    private var projectListObservable: MutableLiveData<Response> = MutableLiveData()
    private var repository : RecipePuppyRepository = RecipePuppyRepository.newInstance()


    fun getRecipeList(query: String) {
        projectListObservable = repository.getRecipeList(query)
    }

    fun getRecipeListObservable(): LiveData<Response>? {
        return projectListObservable
    }

}