package co.com.credibanco.presentation.authorization.request

import co.com.credibanco.presentation.ViewState

sealed class AuthorizationRequestViewState : ViewState {

    data object Initial : AuthorizationRequestViewState() {

        override fun getName() = "AuthorizationRequestViewState.Initial"
    }

    data object Loading : AuthorizationRequestViewState() {

        override fun getName() = "AuthorizationRequestViewState.Loading"
    }

    data object Content : AuthorizationRequestViewState() {

        override fun getName() = "AuthorizationRequestViewState.Content"
    }

    class Error(val message: String) : AuthorizationRequestViewState() {

        override fun getName() = "AuthorizationRequestViewState.Error"
    }
}
