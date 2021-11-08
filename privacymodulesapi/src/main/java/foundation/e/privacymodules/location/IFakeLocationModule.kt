package foundation.e.privacymodules.location

/**
 * Manage a fake location on the device.
 */
interface IFakeLocationModule {
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
