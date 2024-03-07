package presentation.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.Navigator
import presentation.ui.offline.OffLineMovieScreen
import presentation.ui.online.OnlineMoviesScreen

class HomeScreen : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val homeViewModel = getScreenModel<HomeViewModel>()
        val homeState = homeViewModel.homeState.collectAsState().value
        Navigator(HomeScreen()) { navigator ->
            Scaffold(bottomBar = {
                BottomNavigationBar(navigator, homeViewModel)
            }, topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = if (homeState.isCurrentOnlineScreen)
                                "Online"
                            else
                                "Offline",
                            fontSize = 20.sp
                        )
                    },
                    modifier = Modifier.shadow(2.dp),
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        MaterialTheme.colorScheme.inverseOnSurface
                    )
                )
            }) {
                Box(
                    modifier = Modifier.fillMaxSize().padding(it)
                ) { Navigator(OnlineMoviesScreen()) }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    navigator: Navigator,
    homeViewModel: HomeViewModel,
) {
    val items = listOf(
        BottomItem(
            title = "Online", icon = Icons.Rounded.Home
        ), BottomItem(
            title = "Offline", icon = Icons.Rounded.Favorite
        )
    )

    val selected = rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar {
        Row(
            modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            items.forEachIndexed { index, bottomItem ->
                NavigationBarItem(selected = selected.intValue == index, onClick = {
                    selected.intValue = index
                    when (selected.intValue) {
                        0 -> {
                            navigator.pop()
                            navigator.push(OnlineMoviesScreen())
                            homeViewModel.changePage(true)
                        }

                        1 -> {
                            navigator.pop()
                            navigator.push(OffLineMovieScreen())
                            homeViewModel.changePage(false)
                        }
                    }
                }, icon = {
                    Icon(
                        imageVector = bottomItem.icon,
                        contentDescription = bottomItem.title,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }, label = {
                    Text(
                        text = bottomItem.title, color = MaterialTheme.colorScheme.onBackground
                    )
                })
            }
        }
    }

}

data class BottomItem(
    val title: String, val icon: ImageVector
)
