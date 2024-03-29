package com.luxoft.j8airport.flights;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TimezoneUtilsTests {

  @Test
  public void getTimeZonesStartedWithTest1() {
    String filter = "Europe";

    List<String> timeZones = TimezoneUtils.getTimeZonesStartedWith(filter);

    assertTrue("--> list should contains timezones in Europe",
        timeZones.size() > 0);
  }

  @Test
  public void getTimeZonesStartedWithTest2() {
    String filter = "Europe";

    List<String> timeZones = TimezoneUtils.getTimeZonesStartedWith(filter);

    List<String> filteredTimeZones = timeZones
                                         .stream()
                                         .filter(zone -> zone.startsWith(filter))
                                         .collect(Collectors.toList());

    assertEquals("--> list should contains only timezones that starts with Europe",
        filteredTimeZones.size(), timeZones.size());
  }

  @Test
  public void getAllEuropeTimeZonesTest1() {
    List<String> timeZones = TimezoneUtils.getAllEuropeTimeZones();

    List<String> filteredTimeZones = timeZones.stream()
                                         .filter(zone -> zone.startsWith("Europe"))
                                         .collect(Collectors.toList());

    assertEquals("--> list should contains only timezones that starts with Europe",
        filteredTimeZones.size(), timeZones.size());
  }

  @Test
  public void getAllEuropeTimeZonesTest2() {
    List<String> timeZones = TimezoneUtils.getAllEuropeTimeZones();

    int expectedSize = ZoneId.getAvailableZoneIds().stream()
                           .filter(s -> s.startsWith("Europe"))
                           .collect(Collectors.toList()).size();

    assertEquals("--> list should contains " + expectedSize + " time zones for Europe",
        expectedSize, timeZones.size());
  }

  @Test
  public void datePlusDurationTest1() {
    ZonedDateTime source = ZonedDateTime
                               .now(ZoneId.of("Europe/Amsterdam"))
                               .withHour(10)
                               .withMinute(0)
                               .withSecond(0);


    ZonedDateTime target = TimezoneUtils
                               .datePlusDuration(source, Duration.ofMinutes(60), "Europe/Dublin");

    assertEquals("--> time should be equal because Dublin -1 to Amsterdam", source.getHour(), target.getHour());
  }

  @Test
  public void datePlusDurationTest2() {
    ZonedDateTime source = ZonedDateTime
                               .now(ZoneId.of("Europe/Amsterdam"))
                               .withHour(10)
                               .withMinute(0)
                               .withSecond(0);


    ZonedDateTime target = TimezoneUtils
                               .datePlusDuration(source, Duration.ofMinutes(60), "Europe/Athens");

    assertEquals("--> time should be equal because Athens +1 to Amsterdam", source.getHour() + 2, target.getHour());
  }

  @Test
  public void sortedZoneIdToTimeTest() {
    String expected = "Europe/Amsterdam : " + ZonedDateTime.now(ZoneId.of("Europe/Amsterdam"))
                                                  .format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM));

    String actual = TimezoneUtils.sortedZoneIdToTime("Europe").get(0);

    assertEquals("must equals", expected, actual);
  }
}
