package com.example.myapplication.api

import com.example.myapplication.activitys.model.GitResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path


interface GitHubApi {
    @GET("{ownername}/{reponame}/pulls?state=open")
    fun getRequests(@Path("ownername") ownername: String, @Path("reponame") reponame: String): Single<List<GitResponse>>
}