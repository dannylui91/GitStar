package com.example.danny.gitstar.data.model

data class Repo(
        val id: Long,
        val name: String,
        val description: String,
        val stargazers_count: Long,
        val language: String?,
        val forks_count: Long,
        val html_url: String
)