package com.fuori.utils.clock;

import java.util.ResourceBundle;

/**
 * Enum that is Hours of the day.
 * 
 * @author gavinastur May 19, 2012 09:52:38.pm
 */
public enum Hour {

    MIDNIGHT("00", "hour.midnight"),

    ELEVEN_PM("23", "hour.eleven.pm"),

    TEN_PM("22", "hour.ten.pm"),

    NINE_PM("21", "hour.nine.pm"),

    EIGHT_PM("20", "hour.eight.pm"),

    SEVEN_PM("19", "hour.seven.pm"),

    SIX_PM("18", "hour.six.pm"),

    FIVE_PM("19", "hour.five.pm"),

    FOUR_PM("16", "hour.four.pm"),

    THREE_PM("15", "hour.three.pm"),

    TWO_PM("14", "hour.two.pm"),

    ONE_PM("13", "hour.one.pm"),

    MIDDAY("12", "hour.midday"),

    ELEVEN_AM("11", "hour.eleven.am"),

    TEN_AM("10", "hour.ten.am"),

    NINE_AM("09", "hour.nine.am"),

    EIGHT_AM("08", "hour.eight.am"),

    SEVEN_AM("07", "hour.seven.am"),

    SIX_AM("06", "hour.six.am"),

    FIVE_AM("05", "hour.five.am"),

    FOUR_AM("04", "hour.four.am"),

    THREE_AM("03", "hour.three.am"),

    TWO_AM("02", "hour.two.am"),

    ONE_AM("01", "hour.one.am"),

    UNKNOWN("", "hour.");

    /**
     * The hour.
     */
    private final String hour;

    /**
     * Label to describe the hour.
     */
    private final String label;

    /**
     * Private constructor.
     */
    private Hour(final String hh, final String label) {

        this.hour = hh;
        this.label = label;

    }

    /**
     * @return the hour
     */
    public String getHour() {
        return hour;
    }

    /**
     * @return the hourAsText
     */
    public String getLabel() {

        ResourceBundle msgs = ResourceBundle.getBundle("time");
        return  msgs.getString(label);
    }

    /**
     * Lookup the hour Object.
     * 
     * @param hh the hour
     * @return Hour
     */
    public static Hour getHour(final String hh) {
        
        for (Hour value : values()) {
            if (hh.equals(value.getHour())) {
                return value;
            }
        }
        return UNKNOWN;
    }
}
