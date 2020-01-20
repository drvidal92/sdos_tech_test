package es.uvigo.esei.drvidal.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Denís Requejo on 2020-01-20.
 */
object RetrofitFactory {
    private const val BASE_URL = "https://data.ct.gov/resource/hma6-9xbg.json/"

    fun makeRetrofitService(): APIService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(APIService::class.java)
    }
}