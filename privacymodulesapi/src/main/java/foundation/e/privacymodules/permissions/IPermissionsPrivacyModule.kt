package foundation.e.privacymodules.permissions

import android.Manifest.permission.*
import android.graphics.drawable.Drawable
import foundation.e.privacymodules.commons.ManualAction
import foundation.e.privacymodules.permissions.data.AppOpModes
import foundation.e.privacymodules.permissions.data.ApplicationDescription
import foundation.e.privacymodules.permissions.data.PermissionDescription

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

    fun getPermissionDescription(permissionName: String): PermissionDescription


    /**
     * Get the filled up [ApplicationDescription] for the app specified by its [packageName]
     * @param packageName the appId of the app
     * @return the informations about the app.
     */
    fun getApplicationDescription(packageName: String): ApplicationDescription

    /**
     * Check if the current runtime permission is granted for the specified app.
     *
     * @param packageName the packageName of the app
     * @param permissionName the name of the permission in "android.permission.PERMISSION" format.
     * @return the current status for this permission.
     */
    fun isDangerousPermissionGranted(packageName: String, permissionName: String): Boolean


    /**
     * Get the appOps mode for the specified [appOpPermission] of the specified application.
     *
     * @param appDesc the application
     * @param appOpPermissionName the AppOps permission name.
     * @return mode, as a [AppOpModes]
     */
    fun getAppOpMode(appDesc: ApplicationDescription, appOpPermissionName: String): AppOpModes

    /**
     * Grant or revoke the specified permission for the specigfied app.
     * * If their is not enough privileges to get the permission, return the [ManualAction]
     * the user has to perform himself.
     *
     * @param appDesc the application
     * @param permissionName the name of the permission in "android.permission.PERMISSION" format.
     * @param grant true grant the permission, false revoke it.
     * @return null, if the permission is or has just been granted, a [ManualAction] if
     * user has to do it himself.
     */
    fun toggleDangerousPermission(
        appDesc: ApplicationDescription,
        permissionName: String,
        grant: Boolean
    ): ManualAction?


    /**
     * Change the appOp Mode for the specified appOpPermission and application.
     * @param appDesc the application
     * @param appOpPermissionName the AppOps permission name.
     * @return null, if the mode has been changed or if nothing can be done, a [ManualAction] if
     * user has to do it himself.
     */
    fun setAppOpMode(
        appDesc: ApplicationDescription,
        appOpPermissionName: String,
        status: AppOpModes
    ): ManualAction?

    /**
     * Get the permission group associated with this permission, or null.
     */
    fun getPermissionGroup(permission: String): String?

    /**
     * Check that the app has the accessibility service enabled in settings.
     * Return null if the library doesn't use accessibility.
     * @return true if enabled, false if usable but disabled, null if not used.
     */
    fun isAccessibilityEnabled(): Boolean?


    /**
     * Return true if the application is flagged Dangerous.
     */
    fun isPermissionsDangerous(permissionName: String): Boolean

    /**
     * Get the application icon.
     */
    fun getApplicationIcon(packageName: String): Drawable?

    /**
     *  Authorize the specified package to be used as Vpn.
     *  @return true if authorization has been set, false if an error has occurred.
     */
    fun setVpnPackageAuthorization(packageName: String): Boolean

}