package and5.abrar.mvpnews.network

import and5.abrar.mvpnews.model.getAllNewsItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("news")
    fun getAllnews(): Call<List<getAllNewsItem>>
}