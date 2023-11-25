package uz.gita.jaxongir.adminformapp.presentation.componentsscreen

import uz.gita.jaxongir.adminformapp.presentation.main.MainScreen
import uz.gita.jaxongir.adminformapp.utils.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface ComponentDirection{
    suspend fun backToMain()
}

@Singleton
class ComponentDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
): ComponentDirection {
    override suspend fun backToMain() {
        navigator.replaceAll(MainScreen())
    }
}