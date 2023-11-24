package uz.gita.jaxongir.adminformapp.presentation.componentsscreen

import uz.gita.jaxongir.adminformapp.presentation.main.MainScreen
import uz.gita.jaxongir.adminformapp.utils.navigation.AppNavigator
import javax.inject.Inject
interface ComponentDirection{
    suspend fun backToMain()
}

class ComponentDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
): ComponentDirection {
    override suspend fun backToMain() {
        navigator.replaceAll(MainScreen())
    }
}