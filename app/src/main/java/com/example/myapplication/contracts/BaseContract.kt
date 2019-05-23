package com.example.myapplication.contracts

interface BaseContract {
    interface View{
        fun showBusyView()
        fun hideBusyView()
    }

    interface Presenter{
        fun dispose()
    }
}