package dev.pablorjd.rickandmorty.data

import dev.pablorjd.rickandmorty.data.response.ResponseWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApiService {

    @GET("/api/character/")
    suspend fun getCharacters(@Query("page") page:Int):ResponseWrapper
}