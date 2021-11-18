package foundation.e.privacymodules.trackers

import android.content.pm.ApplicationInfo

/**
 * Manage trackers blocking and whitelisting.
 */
interface IBlockTrackersPrivacyModule {

    /**
     * Get the state of the blockin module
     * @return true when blocking is enabled, false otherwise.
     */
    fun isBlockingEnabled(): Boolean

    /**
     * Enable blocking, using the previously configured whitelists
     */
    fun enableBlocking()

    /**
     * Disable blocking
     */
    fun disableBlocking()

    /**
     * Set or unset in whitelist the App with the specified uid.
     * @param appUid the uid of the app
     * @param isWhiteListed true, the app will appears in whitelist, false, it won't
     */
    fun setWhiteListed(appUid: Int, isWhiteListed: Boolean)

    /**
     * Set or unset in whitelist the specifid tracked, for the App specified by its uid.
     * @param tracker the tracker
     * @param appUid the uid of the app
     * @param isWhiteListed true, the app will appears in whitelist, false, it won't
     */
    fun setWhiteListed(tracker: Tracker, appUid: Int, isWhiteListed: Boolean)

    /**
     * Return true if nothing has been added to the whitelist : everything is blocked.
     */
    fun isWhiteListEmpty(): Boolean

    /**
     * Return the white listed App, by their UID
     */
    fun getWhiteListedApp(): List<Int>

    /**
     * Return true if the App is whitelisted for trackers blocking.
     */
    fun isWhitelisted(appUid: Int): Boolean


    /**
     * List the white listed trackers for an App specified by it uid
     */
    fun getWhiteList(appUid: Int): List<Tracker>

    /**
     * List apps that can be either blocked or manually whitelisted in PrivacyCentral
     */
    fun getBlockableApps(): List<ApplicationInfo>

    /**
     * Callback interface to get updates about the state of the Block trackers module.
     */
    interface Listener {

        /**
         * Called when the trackers blocking is activated or deactivated.
         * @param isBlocking true when activated, false otherwise.
         */
        fun onBlockingToggle(isBlocking: Boolean)
    }

    fun addListener(listener: Listener)

    fun removeListener(listener: Listener)

    fun clearListeners()
}
