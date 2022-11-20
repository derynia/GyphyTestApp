package com.gyphytestapp.network

class LoadPageException : Exception() {
    override val message: String
        get() = "Page loading error"
}