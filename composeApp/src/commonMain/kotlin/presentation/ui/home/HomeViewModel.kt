package presentation.ui.home

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ScreenModel {
    private var _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()

    fun changePage(isCurrentOnline: Boolean) {
        _homeState.update {
            it.copy(
                isCurrentOnlineScreen = isCurrentOnline
            )
        }
    }
}