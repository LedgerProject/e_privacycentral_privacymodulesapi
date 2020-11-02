package foundation.e.privacymodules.permissions.data

import android.graphics.drawable.Drawable

/**
 * Useful informations to identify and describe an application.
 */
data class ApplicationDescription(
    val packageName: String,
    val uid: Int,
    var label: CharSequence?,
    var icon: Drawable?
)
