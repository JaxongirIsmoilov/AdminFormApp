package uz.gita.jaxongir.adminformapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.jaxongir.adminformapp.data.repository.RepositoryImpl
import uz.gita.jaxongir.adminformapp.domain.repository.Repository

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun repositoryBinder(impl: RepositoryImpl): Repository
}