package foundation.e.privacymodules.permissions.data

data class PermissionDescription(
    val name: String,
    var isDangerous: Boolean,
    val group: String?,
    var label: CharSequence?,
    var description: CharSequence?
)