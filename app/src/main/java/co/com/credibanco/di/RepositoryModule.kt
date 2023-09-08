package co.com.credibanco.di

import co.com.credibanco.data.repository.AnnulmentRepositoryImpl
import co.com.credibanco.data.repository.AuthorizationRepositoryImpl
import co.com.credibanco.data.repository.CredentialsRepositoryImpl
import co.com.credibanco.domain.repository.AnnulmentRepository
import co.com.credibanco.domain.repository.AuthorizationRepository
import co.com.credibanco.domain.repository.CredentialsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCredentialsRepository(credentialsRepositoryImpl: CredentialsRepositoryImpl): CredentialsRepository

    @Binds
    abstract fun bindAuthorizationRepository(authorizationRepositoryImpl: AuthorizationRepositoryImpl): AuthorizationRepository

    @Binds
    abstract fun bindAnnulmentRepository(annulmentRepositoryImpl: AnnulmentRepositoryImpl): AnnulmentRepository
}
