package presentation.ui.home

data class HomeState(
    val onlineMovieListPage: Int = 1,
    val offlineMovieListPage: Int = 1,

    val isCurrentOnlineScreen: Boolean = true,
)