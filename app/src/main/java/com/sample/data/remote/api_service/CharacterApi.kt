package com.sample.data.remote.api_service

import com.sample.BuildConfig
import com.sample.data.remote.network_responses.NetworkResponse
import retrofit2.Response
import retrofit2.http.GET

interface CharacterApi {
    @GET(BuildConfig.GET_URL)
    suspend fun getCharacters(): Response<NetworkResponse>
}
