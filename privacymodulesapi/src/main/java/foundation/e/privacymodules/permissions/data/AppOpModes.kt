package foundation.e.privacymodules.permissions.data

import android.app.AppOpsManager.*
import android.os.Build

enum class AppOpModes(val modeValue: Int) {
    ALLOWED(MODE_ALLOWED),
    IGNORED(MODE_IGNORED),
    ERRORED(MODE_ERRORED),
    DEFAULT(MODE_DEFAULT),
    FOREGROUND(if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) MODE_ALLOWED else MODE_FOREGROUND);

    companion object {
        private val byMode = mapOf(
            FOREGROUND.modeValue to FOREGROUND,
            ALLOWED.modeValue to ALLOWED,
            IGNORED.modeValue to IGNORED,
            ERRORED.modeValue to ERRORED,
            DEFAULT.modeValue to DEFAULT,
            )

        fun getByModeValue(modeValue: Int): AppOpModes {
            return byMode.get(modeValue) ?: DEFAULT
        }
    }
}
