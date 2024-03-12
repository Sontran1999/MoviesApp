package presentation.ui.offline

import domain.model.VideoMedia

data class OfflineVideoState(
    var isLoading: Boolean = false,
    val offlineMovieList: List<VideoMedia> = emptyList(),
)