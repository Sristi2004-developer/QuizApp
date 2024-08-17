package com.example.authentication.activities.models


data class Quiz (
    var id: String = "",
    var title: String = "",
    var questions: MutableMap<String, Question> = mutableMapOf()

){
    constructor() : this (id = "", title = "")
}