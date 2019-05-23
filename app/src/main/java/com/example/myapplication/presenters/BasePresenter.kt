package com.example.myapplication.presenters

import com.example.myapplication.contracts.BaseContract
import io.reactivex.disposables.CompositeDisposable

open class BasePresenter: BaseContract.Presenter {
    val compositeDisposable = CompositeDisposable()

    override fun dispose() {
        compositeDisposable.dispose()
    }
}