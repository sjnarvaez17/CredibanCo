package co.com.credibanco.domain.use_case

import co.com.credibanco.di.DefaultDispatcher
import co.com.credibanco.di.NetworkModule
import co.com.credibanco.domain.model.Credentials
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@ViewModelScoped
class SetupCredentialsUseCase @Inject constructor(
    @DefaultDispatcher coroutineDispatcher: CoroutineDispatcher
) : SuspendUseCase<SetupCredentialsUseCaseParams, NoResult>(coroutineDispatcher) {

    override suspend fun execute(parameters: SetupCredentialsUseCaseParams): NoResult {
        NetworkModule.credentials = parameters.credentials

        return NoResult
    }
}

data class SetupCredentialsUseCaseParams(val credentials: Credentials)
