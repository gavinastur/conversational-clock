package com.fuori.utils.clock;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Enum that is Minutes of an hour.
 * 
 * @author gavinastur May 19, 2012 09:52:07 PM
 */
public enum Minute {

    ZERO("0", "minute.zero"),

    FIVE("5", "minute.five.past"),

    TEN("10", "minute.ten.past"),

    FIFTEEN("15", "minute.quarter.past"),

    TWENTY("20", "minute.twenty.past"),

    TWENTY_FIVE("25", "minute.twentyfive.past"),

    THIRTY("30", "minute.half.past"),

    THIRTY_FIVE("35", "minute.twentyfive.to"),

    FORTY("40", "minute.twenty.to"),

    FORTY_FIVE("45", "minute.quarter.to"),

    FIFTY("50", "minute.ten.to"),

    FIFTY_FIVE("55", "minute.five.to"),

    SIXTY("60", "minute.sixty"),

    JUST_AFTER("", "minute.just.after"),

    JUST_BEFORE("", "minute.just.before"),

    UNKNOWN("", "minute.unknown");

    /**
     * The minute.
     */
    private final String minute;

    /**
     * Label to describe the hour.
     */
    private final String label;

    /**
     * Private constructor.
     * 
     * @param mm the minute
     * @param label the label
     */
    private Minute(final String mm, final String label) {

        this.minute = mm;
        this.label = label;
    }

    /**
     * @return the minute
     */
    public String getMinute() {

        return minute;
    }

    /**
     * @return the label
     */
    public String getLabel(Locale locale) {

        ResourceBundle msgs = ResourceBundle.getBundle("time", locale);
        return  msgs.getString(label);
    }

    /**
     * Lookup the minute Object.
     * 
     * @param mm the minute
     * @return Minute
     */
    public static Minute getMinute(final int mm) {

        for (Minute value : values()) {
            if (String.valueOf(mm).equals(value.getMinute())) {
                return value;
            }
        }
        return UNKNOWN;
    }
}
