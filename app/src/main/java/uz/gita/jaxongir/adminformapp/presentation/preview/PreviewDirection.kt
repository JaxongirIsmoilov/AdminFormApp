package uz.gita.jaxongir.adminformapp.presentation.preview

import uz.gita.jaxongir.adminformapp.data.model.UserData
import uz.gita.jaxongir.adminformapp.presentation.componentsscreen.ComponentScreen
import uz.gita.jaxongir.adminformapp.utils.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface PreviewDirection {
    suspend fun moveToComponentsScreen(userData: UserData)
}

@Singleton
class PreviewDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : PreviewDirection {
    override suspend fun moveToComponentsScreen(userData: UserData) {
        appNavigator.addScreen(ComponentScreen(userData))
    }
}