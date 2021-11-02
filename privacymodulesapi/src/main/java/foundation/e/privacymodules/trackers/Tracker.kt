package foundation.e.privacymodules.trackers

/**
 * Describe a tracker.
 */
data class Tracker(
    val id: Int,
    val label: String,
    val hostname: String?,
    val exodusId: Int?,
    val description: String?,
    val networkSignature: String?
)
