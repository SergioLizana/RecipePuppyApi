package ikigaiworks.recipepuppyapi.api.rest

import ikigaiworks.recipepuppyapi.api.client.RecipePuppyClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit



class RecipePuppyService {

    companion object {
        fun getClient(): RecipePuppyClient{
            val retrofit = Retrofit.Builder()
                    .baseUrl("http://www.recipepuppy.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(RecipePuppyClient::class.java)
        }
    }
}