package com.fuori.utils.clock;

/**
 * Enum that is Minutes of an hour.
 * 
 * @author gavinastur May 19, 2012 09:52:07 PM
 */
public enum Minute {

    FIVE("5", "five past "),

    TEN("10", "ten past "),

    FIFTEEN("15", "a quater past "),

    TWENTY("20", "twenty past "),

    TWENTY_FIVE("25", "twenty five past "),

    THIRTY("30", "half past "),

    THIRTY_FIVE("35", "twenty five to "),

    FORTY("40", "twenty to "),

    FORTY_FIVE("45", "a quater to "),

    FIFTY("50", "ten to "),

    FIFTYFIVE("55", "five to "),

    JUST_AFTER("", "just after "),

    JUST_BEFORE("", "just before "),

    UNKNOWN("", "");

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
    public String getLabel() {

        return label;
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
