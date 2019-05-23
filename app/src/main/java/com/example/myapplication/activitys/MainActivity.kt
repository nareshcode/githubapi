package com.example.myapplication.activitys

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.widget.Adapter
import com.example.myapplication.R
import com.example.myapplication.activitys.adapters.PullRequestAdapter
import com.example.myapplication.activitys.model.GitResponse
import com.example.myapplication.contracts.MainContract
import com.example.myapplication.presenters.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : BaseActivity(), MainContract.View {
    //We can dependencies through dagger
    private val mainPresenter = MainPresenter(this)
    private lateinit var pullAdapter: PullRequestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_fetch_requests
            .setOnClickListener {
                Timber.d("fetch requests clicked")

                mainPresenter.validate(et_owner_name.text.toString().trim(),et_repo_name.text.toString().trim())
            }

        pullAdapter = PullRequestAdapter()

        rv_pull_requests_list.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = pullAdapter
        }
    }

    override fun setAdapterData(data: List<GitResponse>) {
        Timber.d("setting adapter data")

        pullAdapter.setData(data)
    }

    override fun setOwnerError() {
        Timber.d("owner name error")

        et_owner_name.error = getString(R.string.owner_name_req)
    }

    override fun setRepoError() {
        Timber.d("repo name error")

        et_repo_name.error = getString(R.string.repo_name_req)
    }

    override fun showBusyView() {
        Timber.d("show busy view")

        showingBusyView()
    }

    override fun hideBusyView() {
        Timber.d("hide busy view")

        hideingBusyView()
    }

    override fun showApiError(message: String){
        Timber.d("showing api error")

        Snackbar.make(content, message, Snackbar.LENGTH_LONG).show()
    }
}
