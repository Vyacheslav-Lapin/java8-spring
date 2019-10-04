package com.luxoft.j8airport.flights;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import lombok.val;

public class TimezoneUtils {
  /**
   * Returns all time zones for Europe
   */
  public static List<String> getAllEuropeTimeZones() {
    return getTimeZonesStartedWith("Europe");
  }

  /**
   * Returns all time zones according to the provided filter.
   * <p>
   * Example:
   * <p>
   * Filter - Asia, returns all time zones from Asia
   */
  public static List<String> getTimeZonesStartedWith(String filter) {
    val list = new ArrayList<String>();
    for (String s : ZoneId.getAvailableZoneIds())
      if (s.startsWith(filter))
        list.add(s);
    return Collections.unmodifiableList(list);
  }


  /**
   * Adds duration to the source date and covert it to the timezone with provided zoneId.
   *
   * @param date              source date
   * @param durationInMinutes duration to add in minutes
   * @param zoneId            zone id of target date time
   * @return
   */
  public static ZonedDateTime datePlusDuration(ZonedDateTime date,
                                               Duration durationInMinutes,
                                               String zoneId) {
    return ZonedDateTime.ofInstant(
        date.plusMinutes(
            durationInMinutes.toMinutes()
        ).toInstant(),
        ZoneId.of(zoneId));
  }

  /**
   * Idea of this method to look through all ZoneIds with current localized time.
   * <p>
   * Get all available ZoneIds according to provided filter and
   * returns a list of localized time for every timezone.
   * <p>
   * Records should be sorted from A to Z.
   * <p>
   * Format example: Europe/Amsterdam : 9:22:16 AM
   * Use: DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM))
   *
   * @param filter zoneId should start with this filter
   */
  public static List<String> sortedZoneIdToTime(String filter) {
    return TimezoneUtils.getTimeZonesStartedWith(filter)
        .stream()
        .collect(Collectors
                     .toMap(s -> s,
                         s -> ZonedDateTime.now(ZoneId.of(s))
                                  .format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM))))
        .entrySet()
        .stream()
        .map(e -> e.getKey() + " : " + e.getValue())
        .sorted()
        .collect(Collectors.toList());
  }
}
