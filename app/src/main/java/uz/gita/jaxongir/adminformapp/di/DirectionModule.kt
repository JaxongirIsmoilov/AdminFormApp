package uz.gita.jaxongir.adminformapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.jaxongir.adminformapp.presentation.adduser.UserAddDirection
import uz.gita.jaxongir.adminformapp.presentation.adduser.UserAddDirectionImpl
import uz.gita.jaxongir.adminformapp.presentation.components.ComponentDirection
import uz.gita.jaxongir.adminformapp.presentation.components.Contracts
import uz.gita.jaxongir.adminformapp.presentation.main.MainDirection
import uz.gita.jaxongir.adminformapp.presentation.main.MainDirectionImpl

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {

    @Binds
    fun compDirection(impl: ComponentDirection): Contracts.Direction

    @Binds
    fun bindsMainDirection(impl: MainDirectionImpl): MainDirection

    @Binds
    fun bindsAddDirection(impl :UserAddDirectionImpl) : UserAddDirection
}