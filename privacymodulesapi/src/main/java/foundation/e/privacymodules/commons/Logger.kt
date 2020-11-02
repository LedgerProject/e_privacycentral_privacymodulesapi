package foundation.e.privacymodules.commons

import android.util.Log
import foundation.e.privacymodules.BuildConfig

/**
 * Logger for the privacy modules.
 * By default rely on android.util.Log, but allows to set a custom implementation, like allowing
 * automatic bug tracking.
 */
object Logger: ILogger {
    /**
     * An optional custom logger.
     */
    var logger: ILogger? = null

    override fun d(tag: String?, msg: String?) {
        if (BuildConfig.DEBUG) {
            if (logger != null) {
                logger?.d(tag, msg)
            } else {
                Log.d(tag, msg ?: "")
            }
        }
    }

    override fun e(tag: String?, msg: String?) {
        if (logger != null) {
            logger?.e(tag, msg)
        } else {
            Log.e(tag, msg ?: "")
        }
    }

    override fun e(throwable: Throwable) {
        if (logger != null) {
            logger?.e(throwable)
        } else {
            Log.e("LogException", throwable.message, throwable)
        }
    }
}

interface ILogger {
    fun d(tag: String?, msg: String?)

    fun e(tag: String?, msg: String?)

    fun e(throwable: Throwable)
}