package co.com.credibanco.presentation.login

import co.com.credibanco.presentation.ViewState

sealed class LoginViewState : ViewState {

    data object Initial : LoginViewState() {

        override fun getName() = "LoginViewState.Initial"
    }

    data object Loading : LoginViewState() {

        override fun getName() = "LoginViewState.Loading"
    }

    data object Content : LoginViewState() {

        override fun getName() = "LoginViewState.Content"
    }

    class Error(val message: String) : LoginViewState() {

        override fun getName() = "LoginViewState.Error"
    }
}
