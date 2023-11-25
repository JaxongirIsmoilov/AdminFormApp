package uz.gita.jaxongir.adminformapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.jaxongir.adminformapp.presentation.adduser.UserAddDirection
import uz.gita.jaxongir.adminformapp.presentation.adduser.UserAddDirectionImpl
import uz.gita.jaxongir.adminformapp.presentation.componentsscreen.ComponentDirection
import uz.gita.jaxongir.adminformapp.presentation.componentsscreen.ComponentDirectionImpl
import uz.gita.jaxongir.adminformapp.presentation.main.MainDirection
import uz.gita.jaxongir.adminformapp.presentation.main.MainDirectionImpl
import uz.gita.jaxongir.adminformapp.presentation.preview.PreviewDirection
import uz.gita.jaxongir.adminformapp.presentation.preview.PreviewDirectionImpl
import uz.gita.jaxongir.adminformapp.presentation.splash.SplashDirection
import uz.gita.jaxongir.adminformapp.presentation.splash.SplashDirectionImpl

@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {

    @Binds
    fun compDirection(impl: ComponentDirectionImpl): ComponentDirection

    @Binds
    fun bindsMainDirection(impl: MainDirectionImpl): MainDirection

    @Binds
    fun bindUserAddDirection(impl: UserAddDirectionImpl): UserAddDirection

    @Binds
    fun bindSplashDirection(impl: SplashDirectionImpl): SplashDirection

    @Binds
    fun bindsPreviewDirection(impl: PreviewDirectionImpl) : PreviewDirection


}