package presentation.ui.homescreen

import data.model.Video

data class MovieListState(
    val isLoading: Boolean = false,

    val popularMovieListPage: Int = 1,
    val upcomingMovieListPage: Int = 1,

    val isCurrentPopularScreen: Boolean = true,

    val onlineMovieList: List<Video> = emptyList(),
    val offlineMovieList: List<Video> = emptyList(),
)