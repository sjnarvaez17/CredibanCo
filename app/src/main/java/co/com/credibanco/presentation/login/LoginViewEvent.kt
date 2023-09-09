package co.com.credibanco.presentation.login

import co.com.credibanco.presentation.ViewEvent

sealed class LoginViewEvent : ViewEvent {

    data object Initialize : LoginViewEvent() {

        override fun getName() = "LoginViewEvent.Initialize"
    }

    class LoginClicked(
        val commerceCode: String,
        val terminalCode: String
    ) : LoginViewEvent() {

        override fun getName() = "LoginViewEvent.LoginClicked"
    }
}
