package presentation.ui.online

import domain.model.VideoMedia

data class OnlineVideoState(
    var isLoading: Boolean = false,
    var onlineMovieList: List<VideoMedia> = emptyList(),
)