package co.com.credibanco.presentation.authorization.request

import co.com.credibanco.presentation.ViewEvent

sealed class AuthorizationRequestViewEvent : ViewEvent {

    data object Initialize : AuthorizationRequestViewEvent() {

        override fun getName() = "AuthorizationRequestViewEvent.Initialize"
    }

    class AuthorizationRequestClicked(
        val id: String,
        val commerceCode: String,
        val terminalCode: String,
        val amount: String,
        val card: String
    ) : AuthorizationRequestViewEvent() {

        override fun getName() = "AuthorizationRequestViewEvent.AuthorizationRequestClicked"
    }
}
