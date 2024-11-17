package com.springapi.shopsample.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * ZonedDateTimeMapper is a utility class for converting between LocalDateTime and ZonedDateTime.
 */
public class ZonedDateTimeMapper {

    /**
     * Converts a LocalDateTime to a ZonedDateTime using the system default time zone.
     *
     * @param localDateTime the LocalDateTime to convert
     * @return the corresponding ZonedDateTime
     */
    public static ZonedDateTime getZoneLocalDateTime(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        return localDateTime.atZone(zoneId);
    }

    /**
     * Parses a string to a ZonedDateTime using the provided DateTimeFormatter.
     * If the formatter is null, ISO_OFFSET_DATE_TIME is used.
     *
     * @param value the string to parse
     * @param formatter the DateTimeFormatter to use, or null to use ISO_OFFSET_DATE_TIME
     * @return the corresponding ZonedDateTime
     */
    public static ZonedDateTime getZoneLocalDateTime(String value, DateTimeFormatter formatter) {
        if (formatter == null) {
            formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
        }
        return ZonedDateTime.parse(value, formatter);
    }

    /**
     * Converts a ZonedDateTime to a LocalDateTime using the system default time zone.
     *
     * @param zonedDateTime the ZonedDateTime to convert
     * @return the corresponding LocalDateTime
     */
    public static LocalDateTime convertToLocalDateTime(ZonedDateTime zonedDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime serverZonedDateTime = zonedDateTime.withZoneSameInstant(zoneId);
        return serverZonedDateTime.toLocalDateTime();
    }

}