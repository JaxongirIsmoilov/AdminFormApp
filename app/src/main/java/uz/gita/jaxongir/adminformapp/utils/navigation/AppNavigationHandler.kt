package uz.gita.jaxongir.adminformapp.utils.navigation

import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.flow.SharedFlow

typealias NavigationArgs = Navigator.() -> Unit

interface AppNavigationHandler {
    val uiNavigator: SharedFlow<NavigationArgs>
}