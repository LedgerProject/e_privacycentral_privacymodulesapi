package foundation.e.privacymodules.permissions

import android.app.AppOpsManager
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.pm.PermissionInfo
import android.content.pm.PermissionInfo.PROTECTION_DANGEROUS
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import foundation.e.privacymodules.commons.Logger
import foundation.e.privacymodules.permissions.data.AppOpModes
import foundation.e.privacymodules.permissions.data.ApplicationDescription
import foundation.e.privacymodules.permissions.data.PermissionDescription
import java.lang.Exception

/**
 * Implementation of the commons functionality between privileged and standard
 * versions of the module.
 * @param context an Android context, to retrieve packageManager for example.
 */
abstract class APermissionsPrivacyModule(protected val context: Context): IPermissionsPrivacyModule {

    /**
     * @see IPermissionsPrivacyModule.getInstalledApplications
     */
    override fun getInstalledApplications(): List<ApplicationDescription> {
        val appInfos = context.packageManager.getInstalledApplications(0)
        return appInfos.map { buildApplicationDescription(it) }
    }

    /**
     * @see IPermissionsPrivacyModule.getInstalledApplications
     */
    override fun getApplicationDescription(packageName: String): ApplicationDescription {
        val appDesc = buildApplicationDescription(context.packageManager.getApplicationInfo(packageName, 0))
        appDesc.icon = getApplicationIcon(appDesc.packageName)
        return appDesc
    }

    /**
     * * @see IPermissionsPrivacyModule.getPermissions
     */
    override fun getPermissions(packageName: String): List<String> {
        val packageInfo = context.packageManager.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS)
        return packageInfo.requestedPermissions?.asList() ?: emptyList()
    }

    override fun getPermissionDescription(permissionName: String): PermissionDescription {
        val info = context.packageManager.getPermissionInfo(permissionName, 0)
        return PermissionDescription(
            name = permissionName,
            isDangerous = isPermissionsDangerous(info),
            group = getPermissionGroup(permissionName),
            label = info.loadLabel(context.packageManager),
            description = info.loadDescription(context.packageManager)
        )
    }

    /**
     * @see IPermissionsPrivacyModule.isDangerousPermissionGranted
     */
    override fun isDangerousPermissionGranted(packageName: String, permissionName: String): Boolean {
        return context.packageManager
            .checkPermission(permissionName, packageName) == PackageManager.PERMISSION_GRANTED
    }

    // TODO: on google version, work only for the current package.
    override fun getAppOpMode(
        appDesc: ApplicationDescription,
        appOpPermissionName: String
    ): AppOpModes {

        val appOps = context.getSystemService(AppCompatActivity.APP_OPS_SERVICE) as AppOpsManager

        val mode = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            appOps.checkOpNoThrow(appOpPermissionName,

                appDesc.uid, appDesc.packageName)
        } else {
            appOps.unsafeCheckOpNoThrow(
                appOpPermissionName,
                appDesc.uid, appDesc.packageName)
        }

        return AppOpModes.getByModeValue(mode)
    }

    override fun isPermissionsDangerous(permissionName: String): Boolean {
        try {
            val permissionInfo = context.packageManager.getPermissionInfo(permissionName, 0)
            return isPermissionsDangerous(permissionInfo)
        } catch (e: Exception) {
            Logger.d("PermissionsModule", e.message)
            return false
        }
    }

    private fun isPermissionsDangerous(permissionInfo: PermissionInfo): Boolean {
        try {
            return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
                permissionInfo.protectionLevel and PROTECTION_DANGEROUS == 1
            } else {
                permissionInfo.protection == PROTECTION_DANGEROUS
            }
        } catch (e: Exception) {
            Logger.d("PermissionsModule", e.message)
            return false
        }
    }


    override fun getPermissionGroup(permission: String): String? {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            try {
                context.packageManager.getPermissionInfo(permission, 0).group
            } catch (e: Exception) {
                null
            }
        } else {
            PermissionsGroup.PLATFORM_PERMISSIONS[permission]
        }
    }

    private fun buildApplicationDescription(appInfo: ApplicationInfo): ApplicationDescription {
        return ApplicationDescription(
            packageName = appInfo.packageName,
            uid = appInfo.uid,
            label = getAppLabel(appInfo),
            icon = null
        )
    }

    private fun getAppLabel(appInfo: ApplicationInfo): CharSequence {
        return context.packageManager.getApplicationLabel(appInfo)
    }

    override fun getApplicationIcon(packageName: String): Drawable? {
        return context.packageManager.getApplicationIcon(packageName)
    }
}
