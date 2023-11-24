package uz.gita.jaxongir.adminformapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent


@InstallIn(ViewModelComponent::class)
@Module
interface DirectionModule {
    @[Binds]
    fun bindUserHomeDirection(  )
}