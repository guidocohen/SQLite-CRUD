package com.example.sqlite.utilidades

interface HttpResponse {

    fun httpResponseSuccess(response: String)

    fun httpErrorResponse(response: String)
}