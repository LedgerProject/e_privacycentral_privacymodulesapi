package foundation.e.privacymodules.trackers

/**
 * Get reporting about trackers calls.
 */
interface ITrackTrackersPrivacyModule {

    /**
     * List all the trackers encountered for a specific app.
     */
    fun getTrackersForApp(appUid: Int): List<Tracker>

    /**
     * Return the number of encountered trackers since "ever"
     */
    fun getTrackersCount(): Int

    /**
     * Return number of trackers calls by hours, for the last 24hours.
     * TODO: could be "of the previous day" for a first shot
     * @return list of 24 numbers of trackers calls by hours
     */
    fun getPast24HoursTrackersCalls(): List<Int>

    /**
     * Return number of trackers calls by day, for the last 30 days.
     * TODO: could be "of the previous month" for a first shot
     * @return list of 30 numbers of trackers calls by day
     */
    fun getPastMonthTrackersCalls(): List<Int>

    /**
     * Return number of trackers calls by month, for the last 12 month.
     * TODO: could be "of the previous year" for a first shot
     * @return list of 12 numbers of trackers calls by month
     */
    fun getPastYearTrackersCalls(): List<Int>
}