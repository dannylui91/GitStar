package com.example.danny.gitstar.ui.search

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import com.example.danny.gitstar.R
import com.example.danny.gitstar.data.model.Repo
import com.example.danny.gitstar.ui.base.BaseActivity
import com.example.danny.gitstar.util.LinkUtils
import com.example.danny.gitstar.util.ViewUtils
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.error_layout.*
import kotlinx.android.synthetic.main.toolbar.*
import nucleus.factory.RequiresPresenter
import javax.inject.Inject

/**
 * Search organizations on github and return their top 3 most popular repositories
 * */
@RequiresPresenter(SearchPresenter::class)
class SearchActivity : BaseActivity<SearchPresenter>() {

    @Inject
    lateinit var repoAdapter: SearchAdapter

    override val layoutId: Int
        get() = R.layout.activity_search

    override fun onCreate(savedState: Bundle?) {
        super.onCreate(savedState)
        setSupportActionBar(toolbar)

        queryEt.setOnClickListener { focusQueryEt(true) }
        queryEt.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                focusQueryEt(false)
                presenter.getRepos(queryEt.text.toString().trim())
                return@OnEditorActionListener true
            }
            false
        })

        backBtn.setOnClickListener { focusQueryEt(false) }
        closeBtn.setOnClickListener { queryEt.text.clear() }

        repoRv?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = repoAdapter
        }

        repoAdapter.setClickListener(object : SearchAdapter.ClickListener {
            override fun onRepoClick(repo: Repo) {
                LinkUtils.launchLink(this@SearchActivity, repo.html_url)
            }
        })
    }

    private fun focusQueryEt(focus: Boolean) {
        queryEt.isCursorVisible = focus
        if (!focus) {
            ViewUtils.hideSoftKeyboard(this)
        }
    }

    fun onRepoLoadSuccess(repos: List<Repo>) {
        showErrorContainer(repos.isEmpty(), getString(R.string.empty_text))
        repoAdapter.apply {
            setRepos(repos)
            notifyDataSetChanged()
        }
    }

    fun onError(localizedMessage: String?) {
        showErrorContainer(true, null)
        Toast.makeText(this, localizedMessage, Toast.LENGTH_SHORT).show()
    }

    private fun showErrorContainer(showError: Boolean, message: String?) {
        repoRv.visibility = ViewUtils.booleanToVisibility(!showError)
        errorLayout.visibility = ViewUtils.booleanToVisibility(showError)
        errorText.text = message ?: getString(R.string.error_text)
    }
}
