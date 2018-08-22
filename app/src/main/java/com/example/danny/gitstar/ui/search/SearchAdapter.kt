package com.example.danny.gitstar.ui.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.danny.gitstar.R
import com.example.danny.gitstar.data.model.Repo
import com.example.danny.gitstar.util.ColorUtils
import com.example.danny.gitstar.util.ViewUtils
import kotlinx.android.synthetic.main.item_repo.view.*
import javax.inject.Inject

class SearchAdapter @Inject
constructor() : RecyclerView.Adapter<SearchAdapter.RepoViewHolder>() {

    private var repos: List<Repo> = emptyList()
    private var clickListener: ClickListener? = null

    fun setRepos(repos: List<Repo>) {
        this.repos = repos
    }

    fun setClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_repo, parent, false)
        return RepoViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(repos[position])
    }

    override fun getItemCount(): Int {
        return repos.size
    }

    interface ClickListener {
        fun onRepoClick(repo: Repo)
    }

    inner class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var selectedRepo: Repo

        init {
            itemView.setOnClickListener { clickListener?.onRepoClick(selectedRepo) }
        }

        fun bind(repo: Repo) {
            selectedRepo = repo
            itemView.repoNameTv.text = repo.name
            itemView.repoDescTv.text = repo.description
            itemView.repoStarTv.text = repo.stargazers_count.toString()
            itemView.repoLangTv.text = repo.language
            itemView.repoForkTv.text = repo.forks_count.toString()

            ViewUtils.setTextViewDrawableColor(itemView.repoLangTv, ColorUtils.getLangColor(repo.language))
            ViewUtils.setTextViewDrawableColor(itemView.repoStarTv, R.color.lightGray)
            ViewUtils.setTextViewDrawableColor(itemView.repoForkTv, R.color.lightGray)

            if (repo.language == null) {
                itemView.repoLangTv.visibility = View.GONE
            }
        }
    }
}

