package foundation.e.privacymodules.permissions

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable

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
        return appInfos.map(this::buildApplicationDescription)
    }

    /**
     * @see IPermissionsPrivacyModule.getInstalledApplications
     */
    override fun getApplicationDescription(packageName: String): ApplicationDescription {
        return buildApplicationDescription(context.packageManager.getApplicationInfo(packageName, 0))
    }

    /**
     * * @see IPermissionsPrivacyModule.getPermissions
     */
    override fun getPermissions(packageName: String): List<String> {
        val packageInfo = context.packageManager.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS)
        return packageInfo.requestedPermissions?.asList() ?: emptyList()
    }

    private fun buildApplicationDescription(appInfo: ApplicationInfo): ApplicationDescription {
        return ApplicationDescription(
            packageName = appInfo.packageName,
            uid = appInfo.uid,
            label = getAppLabel(appInfo),
            icon = getAppIcon(appInfo.packageName)
        )
    }

    private fun getAppLabel(appInfo: ApplicationInfo): CharSequence {
        return context.packageManager.getApplicationLabel(appInfo)
    }

    private fun getAppIcon(packageName: String): Drawable? {
        return context.packageManager.getApplicationIcon(packageName)
    }
}
