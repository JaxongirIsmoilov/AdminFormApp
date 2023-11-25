package uz.gita.jaxongir.adminformapp.presentation.preview

import uz.gita.jaxongir.adminformapp.presentation.componentsscreen.ComponentScreen
import uz.gita.jaxongir.adminformapp.utils.navigation.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface PreviewDirection {
    suspend fun moveToComponentsScreen(userId: String)
}

@Singleton
class PreviewDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : PreviewDirection {
    override suspend fun moveToComponentsScreen(userId: String) {
        appNavigator.addScreen(ComponentScreen(userId))
    }

}