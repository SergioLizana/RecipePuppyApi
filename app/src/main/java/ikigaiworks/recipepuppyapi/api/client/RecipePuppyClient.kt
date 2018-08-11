package ikigaiworks.recipepuppyapi.api.client

import ikigaiworks.recipepuppyapi.api.model.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface RecipePuppyClient{
    @GET("/api/")
    fun getRecipeList(@Query("q") query: String?): Call<Response>


}