package presentation.ui.offline

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import domain.usecase.GetVideosOffline
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import util.Resource

class OfflineVideosViewModel(private val getVideosOffline: GetVideosOffline) : ScreenModel {
    private var _offlineVideoState = MutableStateFlow(OfflineVideoState())
    val offlineMovieState = _offlineVideoState.asStateFlow()

    init {
        loadOfflineVideos()
    }

    private fun loadOfflineVideos() {
        screenModelScope.launch {
            _offlineVideoState.update { it.copy(isLoading = true) }
            getVideosOffline().collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { videoList ->
                            _offlineVideoState.update {
                                it.copy(
                                    offlineMovieList = videoList, isLoading = false
                                )
                            }
                        }
                    }

                    is Resource.Error -> {
                        _offlineVideoState.update {
                            it.copy(isLoading = false)
                        }

                    }

                    is Resource.Loading -> {
                        _offlineVideoState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }
                }
            }
        }
    }
}