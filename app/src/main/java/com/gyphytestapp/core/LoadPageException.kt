package com.gyphytestapp.core

class LoadPageException : Exception() {
    override val message: String
        get() = "Page loading error"
}