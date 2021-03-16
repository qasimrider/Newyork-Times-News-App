package com.nytimes.newsapp.repositories.news
import com.nytimes.newsapp.dtos.serverObjects.NewsHolder
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsWebService {
    @GET("mostpopular/v2/mostviewed/{sections}/{period}.json?api-key=SABA5UJTAVfNA08109FpgdbKTU2msQzC")
    fun news(
        @Path("sections") sections: String,
        @Path("period") period: Int
    ): Call<NewsHolder>
}