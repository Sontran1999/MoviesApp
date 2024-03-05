package presentation.ui.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import presentation.ui.offlinemoviescreen.OffLineMovieScreen
import presentation.ui.onlinemoviescreen.OnlineMoviesScreen

class HomeScreen:Screen {
    @Composable
    override fun Content() {
        Navigator(HomeScreen()) {
            Scaffold(bottomBar = {
                BottomNavigationBar(it)
            }, topBar = {

            }){

            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    navigator: Navigator,
) {
    val items = listOf(
        BottomItem(
            title = "Online",
            icon = Icons.Rounded.Home
        ), BottomItem(
            title = "Offline",
            icon = Icons.Rounded.Favorite
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
                        0 ->  {
                            navigator.pop()
                            navigator.push(OffLineMovieScreen())
                        }

                        1 -> {
                            navigator.pop()
                            navigator.push(OnlineMoviesScreen())
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
