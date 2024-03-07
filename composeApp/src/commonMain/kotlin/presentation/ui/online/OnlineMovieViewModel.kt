package presentation.ui.online

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import domain.usecase.GetMovies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import util.Resource

class OnlineMovieViewModel(private val getMovies: GetMovies) : ScreenModel {
    private var _onlineMovieState = MutableStateFlow(OnlineMovieState())
    val onlineMovieState = _onlineMovieState.asStateFlow()

    init {
        loadMovies()
    }

    private fun loadMovies() {
        screenModelScope.launch {
            _onlineMovieState.update { it.copy(isLoading = true) }
            getMovies().collectLatest { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { videoList ->
                            _onlineMovieState.update {
                                it.copy(
                                    onlineMovieList = videoList.videos ?: listOf(),
                                    isLoading = false
                                )
                            }
                        }
                    }

                    is Resource.Error -> {
                        _onlineMovieState.update {
                            it.copy(isLoading = false)
                        }

                    }

                    is Resource.Loading -> {
                        _onlineMovieState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }
                }
            }

        }
    }
}