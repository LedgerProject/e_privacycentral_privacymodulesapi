package foundation.e.privacymodules.permissions

import android.Manifest
import android.Manifest.permission_group.*
import android.os.Build
import android.util.ArrayMap

// https://github.com/aosp-mirror/platform_packages_apps_packageinstaller/blob/master/src/com/android/permissioncontroller/permission/utils/Utils.java
// https://github.com/aosp-mirror/platform_packages_apps_packageinstaller/commit/7ec69f14cba18cbffc392ca1d990a82610c1dd46

object PermissionsGroup {
    val PLATFORM_PERMISSIONS = ArrayMap<String, String>()

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            PLATFORM_PERMISSIONS.put(Manifest.permission.READ_CONTACTS, CONTACTS)
            PLATFORM_PERMISSIONS.put(Manifest.permission.WRITE_CONTACTS, CONTACTS);
            PLATFORM_PERMISSIONS.put(Manifest.permission.GET_ACCOUNTS, CONTACTS);

            PLATFORM_PERMISSIONS.put(Manifest.permission.READ_CALENDAR, CALENDAR);
            PLATFORM_PERMISSIONS.put(Manifest.permission.WRITE_CALENDAR, CALENDAR);

            PLATFORM_PERMISSIONS.put(Manifest.permission.SEND_SMS, SMS);
            PLATFORM_PERMISSIONS.put(Manifest.permission.RECEIVE_SMS, SMS);
            PLATFORM_PERMISSIONS.put(Manifest.permission.READ_SMS, SMS);
            PLATFORM_PERMISSIONS.put(Manifest.permission.RECEIVE_MMS, SMS);
            PLATFORM_PERMISSIONS.put(Manifest.permission.RECEIVE_WAP_PUSH, SMS);
            //PLATFORM_PERMISSIONS.put(Manifest.permission.READ_CELL_BROADCASTS, SMS);

            PLATFORM_PERMISSIONS.put(Manifest.permission.READ_EXTERNAL_STORAGE, STORAGE);
            PLATFORM_PERMISSIONS.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, STORAGE);
            PLATFORM_PERMISSIONS.put(Manifest.permission.ACCESS_MEDIA_LOCATION, STORAGE);

            PLATFORM_PERMISSIONS.put(Manifest.permission.ACCESS_FINE_LOCATION, LOCATION);
            PLATFORM_PERMISSIONS.put(Manifest.permission.ACCESS_COARSE_LOCATION, LOCATION);
            PLATFORM_PERMISSIONS.put(Manifest.permission.ACCESS_BACKGROUND_LOCATION, LOCATION);

            PLATFORM_PERMISSIONS.put(Manifest.permission.READ_CALL_LOG, CALL_LOG);
            PLATFORM_PERMISSIONS.put(Manifest.permission.WRITE_CALL_LOG, CALL_LOG);
            PLATFORM_PERMISSIONS.put(Manifest.permission.PROCESS_OUTGOING_CALLS, CALL_LOG);

            PLATFORM_PERMISSIONS.put(Manifest.permission.READ_PHONE_STATE, PHONE);
            PLATFORM_PERMISSIONS.put(Manifest.permission.READ_PHONE_NUMBERS, PHONE);
            PLATFORM_PERMISSIONS.put(Manifest.permission.CALL_PHONE, PHONE);
            PLATFORM_PERMISSIONS.put(Manifest.permission.ADD_VOICEMAIL, PHONE);
            PLATFORM_PERMISSIONS.put(Manifest.permission.USE_SIP, PHONE);
            PLATFORM_PERMISSIONS.put(Manifest.permission.ANSWER_PHONE_CALLS, PHONE);
            PLATFORM_PERMISSIONS.put(Manifest.permission.ACCEPT_HANDOVER, PHONE);

            PLATFORM_PERMISSIONS.put(Manifest.permission.RECORD_AUDIO, MICROPHONE);

            PLATFORM_PERMISSIONS.put(
                Manifest.permission.ACTIVITY_RECOGNITION,
                ACTIVITY_RECOGNITION
            );

            PLATFORM_PERMISSIONS.put(Manifest.permission.CAMERA, CAMERA);

            PLATFORM_PERMISSIONS.put(Manifest.permission.BODY_SENSORS, SENSORS);
        }
    }
}