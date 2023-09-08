package co.com.credibanco.domain.use_case

import co.com.credibanco.di.DefaultDispatcher
import co.com.credibanco.di.NetworkModule
import co.com.credibanco.domain.model.Credentials
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@ViewModelScoped
class GetCurrentCredentialsUseCase @Inject constructor(
    @DefaultDispatcher coroutineDispatcher: CoroutineDispatcher
) : SuspendUseCase<NoParams, SetupCredentialsUseCaseResult>(coroutineDispatcher) {

    override suspend fun execute(parameters: NoParams): SetupCredentialsUseCaseResult {
        val credentials = NetworkModule.credentials
            ?: throw Exception("User not logged. Please close the app and login again.")

        return SetupCredentialsUseCaseResult(credentials)
    }
}

data class SetupCredentialsUseCaseResult(val credentials: Credentials)
