package foundation.e.privacymodules.permissions


/**
 * The statuses that an permission can have.
 */
enum class PermissionStatus {
    // App has the permission.
    GRANTED,
    // App has the permission, but only when the user is using it.
    FOREGROUND,
    // App has not the permission.
    DENIED;
}
