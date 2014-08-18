package com.fuori.utils.clock;

/**
 * Enum that is Hours of the day.
 * 
 * @author gavinastur May 19, 2012 09:52:38 PM
 */
public enum Hour {

    MIDNIGHT("00", "midnight"),

    ELEVEN_PM("23", "eleven pm"),

    TEN_PM("22", "ten pm"),

    NINE_PM("21", "nine pm"),

    EIGHT_PM("20", "eight pm"),

    SEVEN_PM("19", "seven pm"),

    SIX_PM("18", "six pm"),

    FIVE_PM("19", "five pm"),

    FOUR_PM("16", "four pm"),

    THREE_PM("15", "three pm"),

    TWO_PM("14", "two pm"),

    ONE_PM("13", "one pm"),

    MIDDAY("12", "midday"),

    ELEVEN_AM("11", "eleven am"),

    TEN_AM("10", "ten am"),

    NINE_AM("09", "nine am"),

    EIGHT_AM("08", "eight am"),

    SEVEN_AM("07", "seven am"),

    SIX_AM("06", "six am"),

    FIVE_AM("05", "five am"),

    FOUR_AM("04", "four am"),

    THREE_AM("03", "three am"),

    TWO_AM("02", "two am"),

    ONE_AM("01", "one am"),

    UNKNOWN("", "");

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
        return label;
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
