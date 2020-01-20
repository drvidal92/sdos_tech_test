package es.uvigo.esei.drvidal.network

import es.uvigo.esei.drvidal.model.Webservice
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Denís Requejo on 2020-01-20.
 */
interface APIService {

    @GET(".")
    suspend fun getPeaches(@Query("category") category: String, @Query("item") item: String ): Response<List<Webservice>>

}