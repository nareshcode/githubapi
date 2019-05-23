package com.example.myapplication.contracts

import com.example.myapplication.activitys.model.GitResponse

interface MainContract {
    interface View:BaseContract.View{
        fun setAdapterData(data: List<GitResponse>)
        fun setOwnerError()
        fun setRepoError()
        fun showApiError(message: String)
    }

    interface Presenter{
        fun validate(ownername: String, reponame: String)
    }
}