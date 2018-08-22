package com.example.danny.gitstar.data.remote

import com.example.danny.gitstar.data.model.Repo
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface GithubApi {

    @GET("orgs/{org}/repos")
    fun getRepos(@Path("org") org: String): Observable<List<Repo>>

}