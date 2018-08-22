package com.example.danny.gitstar.ui.search

import com.example.danny.gitstar.data.GithubService

import javax.inject.Inject

import nucleus.presenter.RxPresenter
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class SearchPresenter : RxPresenter<SearchActivity>() {

    @Inject lateinit var service: GithubService

    fun getRepos(org: String) {
        restartableLatestCache(GET_REPOS,
                {
                    service.getRepos(org)
                            .flatMap { repos -> Observable.from(repos) }
                            .toSortedList { repo1, repo2 -> (repo2.stargazers_count - repo1.stargazers_count).toInt() }
                            .flatMap { repos -> Observable.from(repos) }
                            .take(3)
                            .toList()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                },
                { mainActivity, repos -> mainActivity.onRepoLoadSuccess(repos) },
                { mainActivity, throwable -> mainActivity.onError(throwable.localizedMessage) })

        start(GET_REPOS)
    }

    companion object {
        private const val GET_REPOS = 0
    }
}
