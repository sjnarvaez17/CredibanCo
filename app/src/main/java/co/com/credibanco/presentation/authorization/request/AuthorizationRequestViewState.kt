package co.com.credibanco.presentation.authorization.request

import co.com.credibanco.presentation.ViewState

sealed class AuthorizationRequestViewState : ViewState {

    class Initialize(
        val commerceCode: String,
        val terminalCode: String
    ) : AuthorizationRequestViewState() {

        override fun getName() = "AuthorizationRequestViewState.Initialize"
    }

    class AuthorizationRequestClicked(
        val id: String,
        val commerceCode: String,
        val terminalCode: String,
        val amount: String,
        val card: String
    ) : AuthorizationRequestViewState() {

        override fun getName() = "AuthorizationRequestViewState.AuthorizationRequestClicked"
    }
}