package presentation.ui.home

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import domain.usecase.GetMovies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import util.Resource

class HomeViewModel(private val getMovies: GetMovies) : ViewModel() {
    private var _movieListState = MutableStateFlow(MovieListState())
    val movieListState = _movieListState.asStateFlow()

    init {
        loadMovies()
    }

    private fun loadMovies() {
        viewModelScope.launch {
            _movieListState.update { it.copy(isLoading = true) }
            getMovies().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { videoList ->
                            _movieListState.update {
                                it.copy(
                                    onlineMovieList = videoList.videos
                                )
                            }

                        }
                    }

                    is Resource.Error -> {
                        _movieListState.update {
                            it.copy(isLoading = false)
                        }

                    }

                    is Resource.Loading -> {
                        _movieListState.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }
                }
            }

        }
    }
}