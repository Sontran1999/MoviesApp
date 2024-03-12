package util

actual fun Long.toFormattedTime(): String {
    val hours = this / 3600
    val minutes = (this % 3600) / 60
    val seconds = this % 60

    return buildString {
        if (hours > 0) {
            append("%02d:".format(hours))
        }
        append("%02d:%02d".format(minutes, seconds))
    }
}