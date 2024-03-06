import android.os.Build
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import presentation.ui.home.HomeViewModel

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun platformModule() = module {

    viewModel {
        HomeViewModel(get())
    }
}