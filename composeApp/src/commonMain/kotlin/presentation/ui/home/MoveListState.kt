package presentation.ui.home

import data.model.Video

data class MovieListState(
    val isLoading: Boolean = false,

    val onlineMovieListPage: Int = 1,
    val offlineMovieListPage: Int = 1,

    val isCurrentOnlineScreen: Boolean = true,

    val onlineMovieList: List<Video> = emptyList(),
    val offlineMovieList: List<Video> = emptyList(),
)