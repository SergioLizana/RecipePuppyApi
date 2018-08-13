package ikigaiworks.recipepuppyapi.api.rest

import ikigaiworks.recipepuppyapi.api.client.RecipePuppyClient
import ikigaiworks.recipepuppyapi.utils.constants.Constants.Companion.URL_PUPPYAPI
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit



class RecipePuppyService {

    companion object {
        fun getClient(): RecipePuppyClient{
            val retrofit = Retrofit.Builder()
                    .baseUrl(URL_PUPPYAPI)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(RecipePuppyClient::class.java)
        }
    }
}