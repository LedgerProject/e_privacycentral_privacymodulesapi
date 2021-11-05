package foundation.e.privacymodules.trackers

/**
 * Describe a tracker.
 */
data class Tracker(
    val id: Int,
    val label: String,
    val hostname: String? = null,
    val exodusId: Int? = null,
    val description: String? = null,
    val networkSignature: String? = null
)
