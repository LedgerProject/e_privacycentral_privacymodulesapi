package foundation.e.privacymodules.commons

import android.content.Context
import android.content.Intent

/**
 * Describe an action that the user has to perform manually
 */
data class ManualAction(
    /**
     * @property code the identifier of this action.
     */
    val code: ManualActionCode,
    /**
     * @property title the title of this action.
     */
    val title: String?,
    /**
     * @property instructions detailed instructions to perform the action.
     */
    val instructions: String?,
    /**
     * @property intent shortcut to help user to perform the action.
     */
    val intent: Intent?
) {
    /**
     * Handy constructor to use localized strings.
     */
    constructor(context: Context, code: ManualActionCode, titleId: Int, instructionsId: Int, intent: Intent): this(code, context.getString(titleId), context.getString(instructionsId), intent)
}


/**
 * List of all proposed ManualActions in PrivacyModules.
 */
enum class ManualActionCode {
    ENABLE_DEVELOPER_SETTINGS,
    SELECT_MOCK_LOCATION_APP,
    SWITCH_RUNTIME_PERMISSION,
}
