package presentation.ui.online

import data.model.Video

data class OnlineMovieState (
    var isLoading: Boolean = false,
    var onlineMovieList: List<Video> = emptyList(),
    val offlineMovieList: List<Video> = emptyList(),
)