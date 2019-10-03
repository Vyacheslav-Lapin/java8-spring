package com.luxoft.j8airport.flights;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.List;

public class TimezoneUtils
{
    /**
     * Returns all time zones for Europe
     */
    public static List<String> getAllEuropeTimeZones()
    {
        return null;
    }

    /**
     * Returns all time zones according to the provided filter.
     *
     * Example:
     *
     * Filter - Asia, returns all time zones from Asia
     *
     *
     */
    public static List<String> getTimeZonesStartedWith(String filter)
    {
        return null;
    }


    /**
     *
     * Adds duration to the source date and covert it to the timezone with provided zoneId.
     *
     * @param date source date
     * @param durationInMinutes duration to add in minutes
     * @param zoneId zone id of target date time
     * @return
     */
    public static ZonedDateTime datePlusDuration(ZonedDateTime date, Duration durationInMinutes, String zoneId)
    {
        return null;
    }

    /**
     * Idea of this method to look through all ZoneIds with current localized time.
     *
     * Get all available ZoneIds according to provided filter and
     * returns a list of localized time for every timezone.
     *
     * Records should be sorted from A to Z.
     *
     * Format example: Europe/Amsterdam : 9:22:16 AM
     * Use: DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM))
     *
     *
     * @param filter zoneId should start with this filter
     */
    public static List<String> sortedZoneIdToTime(String filter)
    {
        return null;
    }
}
