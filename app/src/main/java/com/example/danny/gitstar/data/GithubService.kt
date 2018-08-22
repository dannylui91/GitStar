package com.example.danny.gitstar.data

import com.example.danny.gitstar.data.model.Repo
import com.example.danny.gitstar.data.remote.GithubApi
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class GithubService @Inject
constructor(private val githubApi: GithubApi) {

    fun getRepos(org: String): Observable<List<Repo>> {
        return githubApi.getRepos(org)
    }
}