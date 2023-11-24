package uz.gita.jaxongir.adminformapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.jaxongir.adminformapp.presentation.components.ComponentDirection
import uz.gita.jaxongir.adminformapp.presentation.components.Contracts

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {

    @Binds
    fun compDirection(impl: ComponentDirection): Contracts.Direction
}