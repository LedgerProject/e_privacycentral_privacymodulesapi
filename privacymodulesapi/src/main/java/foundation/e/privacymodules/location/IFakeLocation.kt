package foundation.e.privacymodules.location

import foundation.e.privacymodules.commons.ManualAction

/**
 * Manage a fake location on the device.
 */
interface IFakeLocation {

    /**
     * Check that the app currently has the required permissions to fake the location of
     * the device.
     * @return true if granted.
     */
    fun hasPermission(): Boolean

    /**
     * Start the proces to obtain the system permission to fake the location.
     * If their is not enough privileges to get the permission, return the [ManualAction]
     * the user has to perform himself.
     * @return null, if the permission is or has just been granted, a [ManualAction] if
     * user has to do it himself.
     */
    fun requirePermission(): ManualAction?

    /**
     * Start to fake the location module. Call [setFakeLocation] after to set the fake
     * position.
     */
    fun startFakeLocation()

    /**
     * Set or update the faked position.
     * @param latitude the latitude of the fake position in degrees.
     * @param longitude the longitude of the fake position in degrees.
     */
    fun setFakeLocation(latitude: Double, longitude: Double)

    /**
     * Stop the fake location module, giving back hand to the true location modules.
     */
    fun stopFakeLocation()
}
