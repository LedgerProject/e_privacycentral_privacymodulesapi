package foundation.e.privacymodules.trackers

/**
 * Get reporting about trackers calls.
 */
interface ITrackTrackersPrivacyModule {


    fun getTrackersForApp(appUid: Int): List<Tracker>

    /**
     * Return the number of called distincts trackers since "ever"
     */
    fun getTrackersCount(): Int

    /**
     * Return the number of called distincts trackers for the last 24 hours
     */
    fun getLast24HoursTrackersCount(): Int

    /**
     * Return number of trackers calls by hours, for the last 24hours.
     * @return list of 24 numbers of trackers calls by hours
     */
    fun getPast24HoursTrackersCalls(): List<Int>

    /**
     * Return number of trackers calls by day, for the last 30 days.
     * @return list of 30 numbers of trackers calls by day
     */
    fun getPastMonthTrackersCalls(): List<Int>

    /**
     * Return number of trackers calls by month, for the last 12 month.
     * @return list of 12 numbers of trackers calls by month
     */
    fun getPastYearTrackersCalls(): List<Int>
}