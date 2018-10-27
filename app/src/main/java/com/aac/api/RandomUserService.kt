package com.aac.api

import com.aac.data.Result
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserService {
    @GET("api?inc=Email&noinfo")
    fun generate(@Query("results") num: Int): Deferred<Response<Result>>
}