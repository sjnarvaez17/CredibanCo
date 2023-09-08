package co.com.credibanco.presentation.authorization.list

import co.com.credibanco.domain.model.Authorization
import co.com.credibanco.presentation.ViewState

sealed class AuthorizationListViewState : ViewState {

    data object Initial : AuthorizationListViewState() {

        override fun getName() = "AuthorizationListViewState.Initial"
    }

    data object Loading : AuthorizationListViewState() {

        override fun getName() = "AuthorizationListViewState.Loading"
    }

    class Content(val authorizationList: List<Authorization>) : AuthorizationListViewState() {

        override fun getName() = "AuthorizationListViewState.Content"
    }
}
