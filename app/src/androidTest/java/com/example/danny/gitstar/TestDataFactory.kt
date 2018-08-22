package com.example.danny.gitstar

import com.example.danny.gitstar.data.model.Repo
import java.util.*

object TestDataFactory {

    private fun makeRepo(): Repo {
        return Repo(100L, "name", "desc", 1400L, "java", 400L, "")
    }

    fun makeRepoList(count: Int): List<Repo> {
        return (0 until count).mapTo(ArrayList()) { makeRepo() }
    }


}