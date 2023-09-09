package co.com.credibanco.di

import co.com.credibanco.data.source.local.LocalDataSource
import co.com.credibanco.data.source.local.impl.LocalDataSourceImpl
import co.com.credibanco.data.source.remote.RemoteDataSource
import co.com.credibanco.data.source.remote.impl.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun provideRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    @Singleton
    abstract fun provideLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource
}
