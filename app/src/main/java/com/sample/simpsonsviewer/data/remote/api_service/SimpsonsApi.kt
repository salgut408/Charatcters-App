package com.sample.simpsonsviewer.data.remote.api_service

import com.sample.simpsonsviewer.data.remote.network_responses.SimpsonsResponse
import retrofit2.Response
import retrofit2.http.GET


interface SimpsonsApi {
    @GET("?q=simpsons+characters&format=json")
    suspend fun getCharacters(): Response<SimpsonsResponse>
}