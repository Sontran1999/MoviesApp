import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.core.module.Module

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

@Composable
expect fun VideoPlayer(url: String)