package uz.gita.jaxongir.adminformapp.presentation.splash

import uz.gita.jaxongir.adminformapp.presentation.main.MainScreen
import uz.gita.jaxongir.adminformapp.utils.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface SplashDirection {
    suspend fun moveToUsersScreen()
}

@Singleton
class SplashDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
):SplashDirection{
    override suspend fun moveToUsersScreen() {
        appNavigator.replaceScreen(MainScreen())
    }

}