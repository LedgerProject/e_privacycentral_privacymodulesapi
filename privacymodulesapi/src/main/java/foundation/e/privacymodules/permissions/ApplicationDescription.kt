package foundation.e.privacymodules.permissions

import android.graphics.drawable.Drawable

/**
 * Useful informations to identify and describe an application.
 */
data class ApplicationDescription(
    val packageName: String,
    val uid: Int,
    val label: CharSequence?,
    val icon: Drawable?,
)
