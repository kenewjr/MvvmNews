package and5.abrar.mvpnews.network

import and5.abrar.mvpnews.model.getAllNewsItem
import and5.abrar.mvvmnews.model.GetAllFilmItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("news")
    suspend fun getAllnews(): List<getAllNewsItem>

    @GET("film")
    suspend fun getAllfilm(): List<GetAllFilmItem>
}