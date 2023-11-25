package uz.gita.jaxongir.adminformapp.presentation.componentsscreen

import uz.gita.jaxongir.adminformapp.utils.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface ComponentDirection {
    suspend fun backToComponent()
}

@Singleton
class ComponentDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
) : ComponentDirection {
    override suspend fun backToComponent() {
        navigator.back()
    }
}