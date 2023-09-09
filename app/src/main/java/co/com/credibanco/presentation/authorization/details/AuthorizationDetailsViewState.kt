package co.com.credibanco.presentation.authorization.details

import co.com.credibanco.domain.model.Annulment
import co.com.credibanco.presentation.ViewState

sealed class AuthorizationDetailsViewState :ViewState{

    data object Initial : AuthorizationDetailsViewState() {

        override fun getName() = "AuthorizationDetailsViewState.Initial"
    }

    data object Loading : AuthorizationDetailsViewState() {

        override fun getName() = "AuthorizationDetailsViewState.Loading"
    }

    class Content(val annulment: Annulment) : AuthorizationDetailsViewState() {

        override fun getName() = "AuthorizationDetailsViewState.Content"
    }

    class Error(val message: String) : AuthorizationDetailsViewState() {

        override fun getName() = "AuthorizationDetailsViewState.Error"
    }
}
