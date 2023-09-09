package co.com.credibanco.presentation.authorization.list

import co.com.credibanco.domain.model.Authorization
import co.com.credibanco.presentation.ViewEvent

sealed class AuthorizationListViewEvent : ViewEvent {

    data object Initialize : AuthorizationListViewEvent() {

        override fun getName() = "AuthorizationListViewEvent.Initialize"
    }

    class AuthorizationItemClicked(
        val authorization: Authorization
    ) : AuthorizationListViewEvent() {

        override fun getName() = "AuthorizationListViewEvent.AuthorizationItemClicked"
    }

    class SearchReceiptId(val receiptId: String)  : AuthorizationListViewEvent() {

        override fun getName() = "AuthorizationListViewEvent.SearchReceiptId"
    }
}
