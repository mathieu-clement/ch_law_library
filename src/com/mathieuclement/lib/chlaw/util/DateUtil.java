package com.mathieuclement.lib.chlaw.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    /**
     * Parse "31.12.2013" as a Java date object.
     *
     * @param dateStr Date with format "dd.mm.yyyy"
     * @return Date object matching the date string
     */
    public static Date parseSwissDate(String dateStr) throws ParseException {
        return swissDateFormat.parse(dateStr);
    }

    private static final DateFormat swissDateFormat = new SimpleDateFormat("dd.MM.yyyy");
}
