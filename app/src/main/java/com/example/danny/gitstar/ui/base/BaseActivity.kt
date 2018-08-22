package com.example.danny.gitstar.ui.base

import android.os.Bundle
import com.example.danny.gitstar.App
import nucleus.factory.PresenterFactory
import nucleus.presenter.Presenter
import nucleus.view.NucleusAppCompatActivity

/**
 * Activities that extend this will have injections for both the activity their presenter
 */
abstract class BaseActivity<P : Presenter<*>> : NucleusAppCompatActivity<P>() {

    protected abstract val layoutId: Int

    override fun onCreate(savedState: Bundle?) {
        val factory = super.getPresenterFactory()
        presenterFactory = PresenterFactory {
            val presenter = factory.createPresenter()
            (application as App).getInjector().inject(presenter)
            presenter
        }
        (application as App).getInjector().inject(this)
        super.onCreate(savedState)
        setContentView(layoutId)
    }
}
