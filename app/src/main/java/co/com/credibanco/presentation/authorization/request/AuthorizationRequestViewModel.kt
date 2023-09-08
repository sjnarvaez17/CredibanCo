package co.com.credibanco.presentation.authorization.request

import co.com.credibanco.di.DefaultDispatcher
import co.com.credibanco.domain.use_case.GetCurrentCredentialsUseCase
import co.com.credibanco.domain.use_case.NoParams
import co.com.credibanco.domain.use_case.RequestAuthorizationUseCase
import co.com.credibanco.domain.use_case.RequestAuthorizationUseCaseParams
import co.com.credibanco.domain.use_case.Result
import co.com.credibanco.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.HttpException
import java.lang.StringBuilder
import javax.inject.Inject

@HiltViewModel
class AuthorizationRequestViewModel @Inject constructor(
    private val getCurrentCredentialsUseCase: GetCurrentCredentialsUseCase,
    private val requestAuthorizationUseCase: RequestAuthorizationUseCase,
    @DefaultDispatcher coroutineDispatcher: CoroutineDispatcher
) : BaseViewModel<AuthorizationRequestViewState, AuthorizationRequestViewEvent>(coroutineDispatcher) {

    override fun getInitialState() = AuthorizationRequestViewState.Initial

    override suspend fun processEvent(event: AuthorizationRequestViewEvent) {
        when (event) {
            is AuthorizationRequestViewEvent.Initialize -> processInitializeEvent()
            is AuthorizationRequestViewEvent.AuthorizationRequestClicked -> processAuthorizationRequestClicked(
                event
            )
        }
    }

    private suspend fun processInitializeEvent() {
        when (val result = getCurrentCredentialsUseCase(NoParams)) {
            is Result.Success -> {
                val credentials = result.data.credentials
                setState(
                    AuthorizationRequestViewState.InitializeForm(
                        credentials.commerceCode,
                        credentials.terminalCode
                    )
                )
            }

            is Result.Error -> {
                setState(
                    AuthorizationRequestViewState.Error(
                        result.exception.message.orEmpty()
                    )
                )
            }
        }
    }

    private suspend fun processAuthorizationRequestClicked(
        event: AuthorizationRequestViewEvent.AuthorizationRequestClicked
    ) {
        val id = event.id
        val commerceCode = event.commerceCode
        val terminalCode = event.terminalCode
        val amount = event.amount
        val card = event.card

        if (
            id.isBlank() ||
            commerceCode.isBlank() ||
            terminalCode.isBlank() ||
            amount.isBlank() ||
            card.isBlank()
        ) {
            setState(
                AuthorizationRequestViewState.Error(
                    "All fields are required. Fill empty fields and try again."
                )
            )
            return
        }

        when (
            val result = requestAuthorizationUseCase(
                RequestAuthorizationUseCaseParams(
                    id,
                    commerceCode,
                    terminalCode,
                    amount,
                    card
                )
            )
        ) {
            is Result.Success -> {
                val authorization = result.data.authorization

                if (authorization == null) {
                    setState(
                        AuthorizationRequestViewState.Error(
                            "Bad response from server. Please, try again"
                        )
                    )
                    return
                }

                setState(AuthorizationRequestViewState.Content(authorization))
            }

            is Result.Error -> {
                val exception = result.exception

                val messageBuilder = StringBuilder()
                    .append(exception.message.orEmpty())

                if (exception is HttpException) {
                    val response = exception
                        .response()
                        ?.errorBody()
                        ?.string()
                        .orEmpty()

                    messageBuilder.append("\n\n")
                    messageBuilder.append(response)
                }

                setState(
                    AuthorizationRequestViewState.Error(
                        messageBuilder.toString()
                    )
                )


            }
        }
    }
}
