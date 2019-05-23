package com.example.myapplication.activitys

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.contracts.BaseContract
import com.example.myapplication.presenters.BasePresenter
import com.example.myapplication.presenters.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

open class BaseActivity : AppCompatActivity() {
    private val presenter = BasePresenter()

    fun showingBusyView() {
        ctrlActivityIndicator.visibility = View.VISIBLE
    }

    fun hideingBusyView() {
        ctrlActivityIndicator.visibility = View.GONE
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onPause() {
        super.onPause()

        presenter.dispose()
    }
}
