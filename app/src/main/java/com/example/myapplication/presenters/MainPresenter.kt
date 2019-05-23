package com.example.myapplication.presenters

import com.example.myapplication.api.GithubService
import com.example.myapplication.contracts.MainContract
import com.example.myapplication.activitys.MainActivity
import com.example.myapplication.activitys.model.GitResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainPresenter(private val view: MainActivity): MainContract.Presenter, BasePresenter() {
    override fun validate(ownername: String, reponame: String) {
        Timber.d("fetch pull requests")

        if(ownername.isEmpty()){
            Timber.d("owner name is empty")

            view.setOwnerError()
        }else if(reponame.isEmpty()){
            Timber.d("repo name is empty")

            view.setRepoError()
        }else{
            fetchPullRequests(ownername, reponame)
        }
    }

    private fun fetchPullRequests(ownername: String, reponame: String){
        Timber.d("fetch pull requests")

        GithubService.create()
            .getRequests(ownername, reponame)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{
                Timber.d("subscribed")

                view.showBusyView()

                compositeDisposable.add(it)
            }.doFinally {
                Timber.d("api call done")

                view.hideBusyView()
            }
            .subscribe(::success,::failure)
    }

    private fun success(response: List<GitResponse>){
        Timber.d("successful: ${response}")

        view.setAdapterData(response)
    }

    private fun failure(throwable: Throwable){
        Timber.d("failure: ${throwable.message}")

        throwable.message?.let { view.showApiError(it) }
    }
}