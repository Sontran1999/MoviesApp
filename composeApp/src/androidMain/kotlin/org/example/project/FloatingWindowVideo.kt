package org.example.project

import android.app.Service
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner

// Android module
class FloatingWindowService : Service() {
    override fun onBind(intent: Intent?): IBinder? = null

    private val windowManager get() = getSystemService(WINDOW_SERVICE) as WindowManager

    private lateinit var floatingView: ComposeView

    private lateinit var lifecycleOwner: MyLifecycleOwner

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate() {
        super.onCreate()
        showOverlay()
    }

    private var windowType = 0
    private var windowFlag = 0

    @RequiresApi(Build.VERSION_CODES.O)
    private fun showOverlay() {
        windowType = if (Build.VERSION.SDK_INT <= 25) {
            @Suppress("DEPRECATION")
            WindowManager.LayoutParams.TYPE_SYSTEM_ERROR
        } else {
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        }
        windowFlag = WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            windowType,
            windowFlag,
            PixelFormat.TRANSLUCENT
        )

        floatingView = ComposeView(this)
        floatingView.setContent {
            ComposeView(
                clickScope = {
                    Toast.makeText(this, "Bye bye ComposeView", Toast.LENGTH_LONG).show()
                    stopSelf()
                },
                text = "ComposeView"
            )
        }

        lifecycleOwner = MyLifecycleOwner()
        lifecycleOwner.performRestore(null)
        lifecycleOwner.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)

        floatingView.setViewTreeLifecycleOwner(lifecycleOwner)
        floatingView.setViewTreeSavedStateRegistryOwner(lifecycleOwner)

        windowManager.addView(floatingView, params)
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleOwner.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        if (floatingView.parent != null) {
            floatingView.clearAnimation()
            floatingView.setViewTreeLifecycleOwner(null)
            floatingView.setViewTreeSavedStateRegistryOwner(null)
            windowManager.removeView(floatingView)
        }
    }
}

@Composable
fun ComposeView(
    clickScope: () -> Unit = {},
    colors: ButtonColors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.background),
    text: String,
    border: BorderStroke = BorderStroke(1.5.dp, color = MaterialTheme.colorScheme.background)
) {
    Button(
        elevation = ButtonDefaults.buttonElevation(8.dp),
        shape = RoundedCornerShape(10.dp),
        border = border,
        colors = colors,
        onClick = { clickScope() },
        content = {
            Text(
                modifier = Modifier
                    .padding(
                        start = 10.dp,
                        top = 5.dp,
                        end = 10.dp,
                        bottom = 5.dp,
                    ),
                text = text,
                style = TextStyle(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 23.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            )
        }
    )
}
