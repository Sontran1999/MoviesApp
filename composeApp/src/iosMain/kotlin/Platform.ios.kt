import org.koin.dsl.module
import platform.UIKit.UIDevice
import presentation.ui.home.HomeViewModel

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual fun platformModule() = module {
    factory {
        HomeViewModel(get())
    }
}