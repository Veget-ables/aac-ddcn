package com.aac.api

class Result(val results: List<SuggestionUser>)

data class SuggestionUser(
        var gender: String,
        val name: Name,
        val email: String) {

    data class Name(val title: String, val first: String, val last: String)
}