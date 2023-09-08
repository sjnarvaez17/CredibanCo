package co.com.credibanco.presentation.login

import co.com.credibanco.domain.model.Credentials
import co.com.credibanco.presentation.ViewState

sealed class LoginViewState : ViewState {

    data object Initial : LoginViewState() {

        override fun getName() = "LoginViewState.Initial"
    }

    data object Loading : LoginViewState() {

        override fun getName() = "LoginViewState.Loading"
    }

    class Content(val credentials: Credentials) : LoginViewState() {

        override fun getName() = "LoginViewState.Content"
    }

    class Error(val message: String) : LoginViewState() {

        override fun getName() = "LoginViewState.Error"
    }
}
