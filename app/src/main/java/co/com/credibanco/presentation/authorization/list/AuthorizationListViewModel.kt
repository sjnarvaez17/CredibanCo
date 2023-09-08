package co.com.credibanco.presentation.authorization.list

import co.com.credibanco.di.DefaultDispatcher
import co.com.credibanco.domain.use_case.GetAuthorizationWithReceiptIdUseCase
import co.com.credibanco.domain.use_case.GetAuthorizationWithReceiptIdUseCaseParams
import co.com.credibanco.domain.use_case.GetAuthorizedTransactionsListUseCase
import co.com.credibanco.domain.use_case.NoParams
import co.com.credibanco.domain.use_case.Result
import co.com.credibanco.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class AuthorizationListViewModel @Inject constructor(
    private val getAuthListUseCase: GetAuthorizedTransactionsListUseCase,
    private val searchAuthByReceiptIdUseCase: GetAuthorizationWithReceiptIdUseCase,
    @DefaultDispatcher dispatcher: CoroutineDispatcher
) : BaseViewModel<AuthorizationListViewState, AuthorizationListViewEvent>(dispatcher) {

    override fun getInitialState() = AuthorizationListViewState.Initial

    override suspend fun processEvent(event: AuthorizationListViewEvent) {
        when (event) {
            AuthorizationListViewEvent.Initialize -> processInitializeEvent()
            is AuthorizationListViewEvent.AuthorizationItemClicked -> processAuthorizationItemClicked(
                event
            )

            is AuthorizationListViewEvent.SearchReceiptId -> processSearchByReceiptId(event)
        }
    }

    private suspend fun processInitializeEvent() {
        setState(AuthorizationListViewState.Loading)
        when (val result = getAuthListUseCase(NoParams)) {
            is Result.Success -> {
                setState(AuthorizationListViewState.Content(result.data.authorizations))
            }

            is Result.Error -> {
                toastMessage.postValue(result.exception.message)
                setState(AuthorizationListViewState.Content(emptyList()))
            }
        }
    }

    private fun processAuthorizationItemClicked(
        event: AuthorizationListViewEvent.AuthorizationItemClicked
    ) {
        setState(AuthorizationListViewState.NavigateToAuthDetail(event.authorization))
    }

    private suspend fun processSearchByReceiptId(
        event: AuthorizationListViewEvent.SearchReceiptId
    ) {
        setState(AuthorizationListViewState.Loading)

        when (
            val result = searchAuthByReceiptIdUseCase(
                GetAuthorizationWithReceiptIdUseCaseParams(
                    event.receiptId
                )
            )
        ) {
            is Result.Success -> {
                setState(AuthorizationListViewState.Content(result.data.authorizations))
            }

            is Result.Error -> {
                toastMessage.postValue(result.exception.message)
                setState(AuthorizationListViewState.Content(emptyList()))
            }
        }
    }
}
