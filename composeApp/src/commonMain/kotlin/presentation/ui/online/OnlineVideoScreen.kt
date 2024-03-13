package presentation.ui.online

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

class OnlineVideoScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow.parent
        val onlineVideoViewModel = getScreenModel<OnlineVideoViewModel>()
        val onlineMovieState = onlineVideoViewModel.onlineMovieState.collectAsState().value
        OnlineScreen(onlineMovieState, navigator)
    }
}

@Composable
fun OnlineScreen(
    onlineVideoState: OnlineVideoState,
    navigator: Navigator?,
) {
    if (onlineVideoState.isLoading) {
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
            items(onlineVideoState.onlineMovieList.size) { index ->
                VideoItem(onlineVideoState.onlineMovieList[index], isOnline = true) {
                    navigator?.push(VideoScreen(it))
                }
            }
        }
    }
}