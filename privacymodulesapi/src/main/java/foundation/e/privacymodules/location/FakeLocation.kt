package foundation.e.privacymodules.location

import android.app.AppOpsManager
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.location.Criteria
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Process
import android.os.SystemClock
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * Implementation of the functionality of fake location.
 * All of them are available for normal application, so just one version is enough.
 *
 * @param context an Android context, to retrieve system services for example.
 */
class FakeLocation(protected val context: Context): IFakeLocation {

    /**
     * List of all the Location provider that will be mocked.
     */
    private val providers = listOf(LocationManager.NETWORK_PROVIDER, LocationManager.GPS_PROVIDER)

    /**
     * Handy accessor to the locationManager service.
     * We avoid getting it on module initialization to wait for the context to be ready.
     */
    private val locationManager: LocationManager get() =
        context.getSystemService(LOCATION_SERVICE) as LocationManager

    /**
     * @see IFakeLocation.startFakeLocation
     */
    override fun startFakeLocation() {
        providers.forEach { provider ->
            locationManager.addTestProvider(
                provider,
                false,
                false,
                false,
                false,
                false,
                true,
                true,
                Criteria.POWER_LOW, Criteria.ACCURACY_FINE)
            locationManager.setTestProviderEnabled(provider, true)
        }
    }

    /**
     * @see IFakeLocation.setFakeLocation
     */
    override fun setFakeLocation(latitude: Double, longitude: Double) {
        providers.forEach { provider ->
            val location = Location(provider)
            location.latitude = latitude
            location.longitude = longitude

            // Set default value for all the other required fields.
            location.altitude = 3.0
            location.time =System.currentTimeMillis()
            location.speed = 0.01f
            location.bearing = 1f
            location.accuracy = 3f

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                location.bearingAccuracyDegrees =0.1f
                location.verticalAccuracyMeters = 0.1f
                location.speedAccuracyMetersPerSecond = 0.01f
            }

//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//                location.elapsedRealtimeNanos = SystemClock.elapsedRealtimeNanos()
//            }

            locationManager.setTestProviderLocation(provider, location)
        }
    }

    /**
     * @see IFakeLocation.stopFakeLocation
     */
    override fun stopFakeLocation() {
        providers.forEach { provider ->
            locationManager.setTestProviderEnabled(provider, false)
            locationManager.removeTestProvider(provider)
        }
    }
}
