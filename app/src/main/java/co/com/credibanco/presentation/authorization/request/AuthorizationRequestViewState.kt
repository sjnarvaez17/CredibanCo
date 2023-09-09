package co.com.credibanco.presentation.authorization.request

import co.com.credibanco.domain.model.Authorization
import co.com.credibanco.presentation.ViewState

sealed class AuthorizationRequestViewState : ViewState {

    data object Initial : AuthorizationRequestViewState() {

        override fun getName() = "AuthorizationRequestViewState.Initial"
    }

    class InitializeForm(
        val commerceCode: String,
        val terminalCode: String
    ) : AuthorizationRequestViewState() {

        override fun getName() = "AuthorizationRequestViewState.InitializeForm"
    }

    data object Loading : AuthorizationRequestViewState() {

        override fun getName() = "AuthorizationRequestViewState.Loading"
    }

    class Content(val authorization: Authorization) : AuthorizationRequestViewState() {

        override fun getName() = "AuthorizationRequestViewState.Content"
    }

    class Error(val message: String) : AuthorizationRequestViewState() {

        override fun getName() = "AuthorizationRequestViewState.Error"
    }
}
