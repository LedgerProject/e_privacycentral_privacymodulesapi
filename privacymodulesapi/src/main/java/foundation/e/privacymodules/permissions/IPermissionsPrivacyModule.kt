package foundation.e.privacymodules.permissions

import android.Manifest.permission.*
import foundation.e.privacymodules.commons.ManualAction

/**
 * List applications and manage theirs permissions.
 */
interface IPermissionsPrivacyModule {

    /**
     * List all the installed application on the device.
     * @return list of filled up [ApplicationDescription]
     */
    fun getInstalledApplications(): List<ApplicationDescription>

    /**
     * List of permissions names used by an app, specified by its [packageName].
     * @param packageName the appId of the app
     * @return the list off permission, in the "android.permission.PERMISSION" format.
     */
    fun getPermissions(packageName: String): List<String>

    /**
     * Get the filled up [ApplicationDescription] for the app specified by its [packageName]
     * @param packageName the appId of the app
     * @return the informations about the app.
     */
    fun getApplicationDescription(packageName: String): ApplicationDescription



    /**
     * Get the current [PermissionStatus] for the specified runtime permission and app.
     * On low privileges implementations, [PermissionStatus.FOREGROUND] can't be returned.
     *
     * @param uid the uid (linunx user id) of the app
     * @param packageName the appId of the app
     * @param permissionName the name of the permission in "android.permission.PERMISSION" format.
     * @return the current status for this permission.
     */
    fun getPermissionStatus(uid: Int, packageName: String, permissionName: String): PermissionStatus


    /**
     * Set the status for the specified runtime permission and app.
     * * If their is not enough privileges to get the permission, return the [ManualAction]
     * the user has to perform himself.
     *
     * @param uid the uid (linunx user id) of the app
     * @param packageName the appId of the app
     * @param permissionName the name of the permission in "android.permission.PERMISSION" format.
     * @param status the status to set.
     * @return null, if the permission is or has just been granted, a [ManualAction] if
     * user has to do it himself.
     */
    fun setRuntimePermission(uid: Int, packageName: String, permissionName: String, status: PermissionStatus): ManualAction?

    companion object {

        /**
         * List of the runtime permissions : the permission that can be granted or revoqued
         * directly by the used, since Android M.
         */
        val RUNTIME_PERMISSIONS = setOf(
            READ_CALENDAR,
            WRITE_CALENDAR,
            CAMERA,
            READ_CONTACTS,
            WRITE_CONTACTS,
            GET_ACCOUNTS,
            ACCESS_FINE_LOCATION,
            ACCESS_COARSE_LOCATION,
            RECORD_AUDIO,
            READ_PHONE_STATE,
            READ_PHONE_NUMBERS,
            CALL_PHONE,
            ANSWER_PHONE_CALLS,
            READ_CALL_LOG,
            WRITE_CALL_LOG,
            ADD_VOICEMAIL,
            USE_SIP,
            PROCESS_OUTGOING_CALLS,
            BODY_SENSORS,
            SEND_SMS,
            RECEIVE_SMS,
            READ_SMS,
            RECEIVE_WAP_PUSH,
            RECEIVE_MMS,
            READ_EXTERNAL_STORAGE,
            WRITE_EXTERNAL_STORAGE
        )
    }
}