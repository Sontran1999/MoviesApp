package presentation.ui.offline

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import presentation.ui.componentes.LoadingScreen
import presentation.ui.componentes.VideoItem
import presentation.ui.video.VideoScreen

class OffLineVideoScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow.parent
        val offlineVideoViewModel = getScreenModel<OfflineVideosViewModel>()
        val offlineMovieState = offlineVideoViewModel.offlineMovieState.collectAsState().value
        OfflineScreen(offlineMovieState, navigator)
    }

}

@Composable
fun OfflineScreen(
    offlineMovieState: OfflineVideoState,
    navigator: Navigator?,
) {
    if (offlineMovieState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            LoadingScreen()
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp)
        ) {
            items(offlineMovieState.offlineMovieList.size) { index ->
                VideoItem(offlineMovieState.offlineMovieList[index], isOnline = false) {
                    navigator?.push(VideoScreen(it))
                }
            }
        }
    }
}