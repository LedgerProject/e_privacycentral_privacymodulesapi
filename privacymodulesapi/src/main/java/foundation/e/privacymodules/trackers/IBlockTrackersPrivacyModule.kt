package foundation.e.privacymodules.trackers

/**
 * Manage trackers blocking and whitelisting.
 */
interface IBlockTrackersPrivacyModule {

    /**
     * Enable blocking, using the previously configured whitelists
     */
    fun enableBlocking()

    /**
     * Disable blocking
     */
    fun disableBlocking()

    /**
     * Whitelist the App with the specified uid
     */
    fun unblock(appUid: Int)

    /**
     * Whitelist the specified tracker, for the App specified by its uid.
     */
    fun unblock(tracker: Tracker, appUid: Int)

    /**
     * Return true if the App is whitelisted for trackers blocking.
     */
    fun isWhitelisted(appUid: Int): Boolean

    /**
     * List the white listed trackers for an App specified by it uid
     */
    fun getWhiteList(appUid: Int): List<Tracker>

}