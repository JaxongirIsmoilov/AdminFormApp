package uz.gita.jaxongir.adminformapp.presentation.components

import uz.gita.jaxongir.adminformapp.presentation.main.MainScreen
import uz.gita.jaxongir.adminformapp.utils.navigation.AppNavigator
import javax.inject.Inject

class ComponentDirection @Inject constructor(
    private val navigator: AppNavigator
): Contracts.Direction{
    override suspend fun backToMain() {
        navigator.replaceAll(MainScreen())
    }
}