package com.aac.api

import com.aac.data.Result
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserService {
    @GET("api?inc=gender,name,email&noinfo")
    fun generate(@Query("results") num: Int): Flowable<Result>
}