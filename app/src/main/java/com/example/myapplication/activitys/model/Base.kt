package com.example.myapplication.activitys.model


data class Base (

    val label : String,
    val ref : String,
    val sha : String,
    val user : User,
    val repo : Repo
)