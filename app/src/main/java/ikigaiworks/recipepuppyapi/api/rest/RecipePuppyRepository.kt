package ikigaiworks.recipepuppyapi.api.rest

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import ikigaiworks.recipepuppyapi.api.model.Response
import retrofit2.Callback
import android.R.attr.data
import android.util.Log
import android.widget.Toast
import retrofit2.Call


class RecipePuppyRepository {


    fun getRecipeList(observableLiveData: MutableLiveData<Response>,query: String): MutableLiveData<Response>{
        RecipePuppyService.getClient().getRecipeList(query).enqueue(object : Callback<Response> {
            override fun onFailure(call: Call<Response>?, t: Throwable?) {
                observableLiveData.value = null
            }

            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
                observableLiveData.value = response?.body()
            }
        })
        return observableLiveData
    }

    companion object {
        fun newInstance(): RecipePuppyRepository{
            return RecipePuppyRepository()
        }
    }
}