package presentation.ui.online

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import domain.usecase.GetVideosOnline
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import util.Resource

class OnlineVideoViewModel(
    private val getVideosOnline: GetVideosOnline
) : ScreenModel {
    private var _onlineVideoState = MutableStateFlow(OnlineVideoState())
    val onlineMovieState = _onlineVideoState.asStateFlow()

    init {
        loadOnlineVideos()
    }

    private fun loadOnlineVideos() {
        screenModelScope.launch {
            _onlineVideoState.update { it.copy(isLoading = true) }
            getVideosOnline().collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { videoList ->
                            _onlineVideoState.update {
                                it.copy(
                                    onlineMovieList = videoList,
                                    isLoading = false
                                )
                            }
                        }
                    }

                    is Resource.Error -> {
                        _onlineVideoState.update {
                            it.copy(isLoading = false)
                        }

                    }

                    is Resource.Loading -> {
                        _onlineVideoState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }
                }
            }
        }
    }
}