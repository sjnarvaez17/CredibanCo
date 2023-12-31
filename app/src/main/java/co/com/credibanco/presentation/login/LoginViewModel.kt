package co.com.credibanco.presentation.login

import co.com.credibanco.di.DefaultDispatcher
import co.com.credibanco.di.NetworkModule
import co.com.credibanco.domain.use_case.LoginUseCase
import co.com.credibanco.domain.use_case.LoginUseCaseParams
import co.com.credibanco.domain.use_case.LoginUseCaseResult
import co.com.credibanco.domain.use_case.Result
import co.com.credibanco.domain.use_case.SetupCredentialsUseCase
import co.com.credibanco.domain.use_case.SetupCredentialsUseCaseParams
import co.com.credibanco.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val setupCredentialsUseCase: SetupCredentialsUseCase,
    @DefaultDispatcher dispatcher: CoroutineDispatcher
) : BaseViewModel<LoginViewState, LoginViewEvent>(dispatcher) {

    override fun getInitialState() = LoginViewState.Initial

    override suspend fun processEvent(event: LoginViewEvent) {
        when (event) {
            LoginViewEvent.Initialize -> Unit
            is LoginViewEvent.LoginClicked -> onLoginClicked(event)
        }
    }

    private suspend fun onLoginClicked(event: LoginViewEvent.LoginClicked) {
        val commerceCode = event.commerceCode
        val terminalCode = event.terminalCode

        if (commerceCode.isBlank() || terminalCode.isBlank()) {
            setState(LoginViewState.Error("Fields can't be empty"))
            return
        }

        if (commerceCode.length != 6 || terminalCode.length != 6) {
            setState(LoginViewState.Error("Invalid input"))
            return
        }

        when (
            val result: Result<LoginUseCaseResult> = loginUseCase(
                LoginUseCaseParams(commerceCode, terminalCode)
            )
        ) {
            is Result.Success -> {
                val credentials = result.data.credentials

                setupCredentialsUseCase(
                    SetupCredentialsUseCaseParams(credentials)
                )

                setState(LoginViewState.Content)
            }

            is Result.Error -> {
                setState(LoginViewState.Error(result.exception.message.orEmpty()))
            }
        }
    }
}