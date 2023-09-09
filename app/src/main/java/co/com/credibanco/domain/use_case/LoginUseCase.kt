package co.com.credibanco.domain.use_case

import co.com.credibanco.di.DefaultDispatcher
import co.com.credibanco.domain.model.Credentials
import co.com.credibanco.domain.repository.CredentialsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@ViewModelScoped
class LoginUseCase @Inject constructor(
    private val credentialsRepository: CredentialsRepository,
    @DefaultDispatcher coroutineDispatcher: CoroutineDispatcher
) : SuspendUseCase<LoginUseCaseParams, LoginUseCaseResult>(coroutineDispatcher) {

    override suspend fun execute(parameters: LoginUseCaseParams): LoginUseCaseResult {
        credentialsRepository.saveCredentials(
            parameters.commerceCode,
            parameters.terminalCode
        )

        val credentials: Credentials? = credentialsRepository
            .getCredentials(
                parameters.commerceCode,
                parameters.terminalCode
            )

        return if (credentials == null) {
            throw Exception("Invalid credentials. Please try again.")
        } else {
            LoginUseCaseResult(
                credentials
            )
        }
    }
}

data class LoginUseCaseParams(
    val commerceCode: String,
    val terminalCode: String
)

data class LoginUseCaseResult(val credentials: Credentials)
