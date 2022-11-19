package com.gyphytestapp.state

interface IAuthUiState {
    val isSuccessful: Boolean
    val errorMessage: String?
    fun errorMessageAsString() = errorMessage

    fun hasError() = !errorMessage.isNullOrEmpty()
}