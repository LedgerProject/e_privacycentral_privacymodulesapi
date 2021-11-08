package foundation.e.privacymodules.location


import android.app.*
import android.content.Context
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class FakeLocationService: Service() {

    enum class Actions {
        START_FAKE_LOCATION,
        REQUEST_STATUS
    }

    enum class Status {
        UPDATING_FAKE_LOCATION,
        IDLE
    }

    companion object {
        private const val PERIOD_LOCATION_UPDATE = 1000L
        private const val PERIOD_UPDATES_SERIE = 2 * 60 * 1000L
        private const val NOTIFICATION_ID = 110

        private const val PARAM_LATITUDE = "PARAM_LATITUDE"
        private const val PARAM_LONGITUDE = "PARAM_LONGITUDE"

        fun buildFakeLocationIntent(context: Context, latitude: Double, longitude: Double): Intent {
            return Intent(context, FakeLocationService::class.java).apply {
                action = Actions.START_FAKE_LOCATION.name
                putExtra(PARAM_LATITUDE, latitude)
                putExtra(PARAM_LONGITUDE, longitude)
            }
        }

        fun buildStatusIntent(context: Context): Intent {
            return Intent(context, FakeLocationService::class.java).apply {
                action = Actions.REQUEST_STATUS.name
            }
        }

        fun buildStopIntent(context: Context) = Intent(context, FakeLocationService::class.java)
    }

    private lateinit var fakeLocationModule: FakeLocationModule
    //private val localBroadcastManager get() = LocalBroadcastManager.getInstance(this)

    private var countDownTimer: CountDownTimer? = null
    private var status = Status.IDLE
    private var fakeLocation: Pair<Double, Double>? = null

    override fun onCreate() {
        super.onCreate()
        fakeLocationModule = FakeLocationModule(applicationContext)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (Actions.valueOf(it.action)) {
                Actions.START_FAKE_LOCATION -> {
                    startForeground(NOTIFICATION_ID, generateForegroundNotification())

                    fakeLocation = Pair(
                        it.getDoubleExtra(PARAM_LATITUDE, 0.0),
                        it.getDoubleExtra(PARAM_LONGITUDE, 0.0)
                    )

                    status = Status.UPDATING_FAKE_LOCATION
                    initTimer()
                    sendCurrentStatus()
                }
                Actions.REQUEST_STATUS -> sendCurrentStatus()
            }
        }

        return START_STICKY
    }

    override fun onDestroy() {
        countDownTimer?.cancel()
        status = Status.IDLE
        sendCurrentStatus()
        removeForegroundNotification()
        super.onDestroy()
    }

    private fun sendCurrentStatus() {
        val intent = Intent(status.name)
        //localBroadcastManager.sendBroadcast(intent)
    }

    private fun initTimer() {
        countDownTimer?.cancel()
        countDownTimer = object: CountDownTimer(PERIOD_UPDATES_SERIE, PERIOD_LOCATION_UPDATE) {
            override fun onTick(millisUntilFinished: Long) {
                if (status == Status.UPDATING_FAKE_LOCATION) fakeLocation?.let {
                    val random = System.currentTimeMillis() % 100 // For debug.
                    try {
                        fakeLocationModule.setTestProviderLocation(
                            it.first,
                            it.second + random * 0.01
                        )
                    } catch (e: Exception) {
                        Log.d("FakeLocationService", "setting fake location", e)
                    }
                }
            }

            override fun onFinish() {
                if (status == Status.UPDATING_FAKE_LOCATION) {
                    initTimer()
                }
            }
        }.start()
    }

    private fun generateForegroundNotification(): Notification {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.createNotificationChannelGroup(NotificationChannelGroup("testid", "testname"))
        notificationManager.createNotificationChannel(NotificationChannel("testchannelId", "testchannelname", NotificationManager.IMPORTANCE_MIN))

        return NotificationCompat.Builder(this, "testchannelId").build()
    }

    private fun removeForegroundNotification() {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(NOTIFICATION_ID)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}
