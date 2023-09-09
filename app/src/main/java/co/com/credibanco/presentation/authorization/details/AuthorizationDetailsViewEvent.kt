package co.com.credibanco.presentation.authorization.details

import co.com.credibanco.presentation.ViewEvent

sealed class AuthorizationDetailsViewEvent : ViewEvent {

    data object Initialize : AuthorizationDetailsViewEvent() {

        override fun getName() = "AuthorizationDetailsViewEvent.Initialize"
    }

    class RequestAnnulment(
        val receiptId: String,
        val rrn: String
    ) : AuthorizationDetailsViewEvent() {

        override fun getName() = "AuthorizationDetailsViewEvent.RequestAnnulment"
    }
}
