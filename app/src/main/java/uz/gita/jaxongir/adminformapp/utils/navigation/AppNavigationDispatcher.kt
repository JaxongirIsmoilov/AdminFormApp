package uz.gita.jaxongir.adminformapp.utils.navigation

import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppNavigationDispatcher @Inject constructor() : AppNavigator, AppNavigationHandler {
    override val uiNavigator = MutableSharedFlow<NavigationArgs>()

    private suspend fun mNavigate(navigator: NavigationArgs) {
        uiNavigator.emit(navigator)
    }

    override suspend fun addScreen(screen: MyScreen) = mNavigate {
        push(screen)
    }

    override suspend fun replaceScreen(screen: MyScreen) = mNavigate {
        replace(screen)
    }

    override suspend fun back() = mNavigate {
        pop()
    }

    override suspend fun backUntil(screen: MyScreen) = mNavigate {
        popUntil { it == screen }
    }

    override suspend fun backToRoot() = mNavigate {
        popUntilRoot()
    }

    override suspend fun replaceAll(screen: MyScreen) {
        replaceAll(screen)
    }
}