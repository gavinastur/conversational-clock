package com.fuori.utils.clock;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * The Conversational Clock that responds with a polite response when asked for the time.
 * 00:00 is "midnight" 
 * 00:05 is "five past midnight" 
 * 00:01 is "just after midnight" 
 * 23:45 is "a quarter to midnight"
 * 
 * @author gavinastur May 19, 2012 09:34:48 PM
 */
public final class ConversationalClock {

    /**
     * Method to to get the current time in polite text. e.g. 00:05 is "five past midnight".
     * 
     * @return String formatted as text e.g. "five past midnight"
     */
    public static String getCurrentTime(Locale locale) {

        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);

        return getTime(hour, minute, locale);
    }

    /**
     * Method to to get the time in polite text. e.g. 00:05 is "five past midnight"
     * 
     * @param milliseconds time in milliseconds to be converted
     * @return String formatted as text e.g. "five past midnight"
     */
    public static String getTime(final long milliseconds) {

        int minute = (int) (TimeUnit.MILLISECONDS.toMinutes(milliseconds) % 60);
        int hour = (int) (TimeUnit.MILLISECONDS.toHours(milliseconds) % 24);

        return getTime(hour, minute, Locale.ENGLISH);
    }

    /**
     * Method to to get the time in polite text. e.g. 00:05 is "five past midnight".
     *
     * @param hourOfDay The hour you wish to convert
     * @param minute The minute you wish to convert
     * @return String formatted as text e.g. "five past midnight"
     */
    public static String getTime(final int hourOfDay, final int minute) {
        return getTime(hourOfDay, minute, Locale.ENGLISH);
    }
    /**
     * Method to to get the time in polite text. e.g. 00:05 is "five past midnight".
     * 
     * @param hourOfDay The hour you wish to convert
     * @param minute The minute you wish to convert
     * @return String formatted as text e.g. "five past midnight"
     */
    public static String getTime(final int hourOfDay, final int minute, Locale locale) {

        if ((hourOfDay > 23 || hourOfDay < 0) || (minute > 59 || minute < 0))  {
            throw new RuntimeException(hourOfDay + ":" + minute + " is not valid. hourOfDay must be 0-23. minute must be 0-59.");
        }

        final StringBuilder sb = new StringBuilder();

        int mod = minute % 10;

        switch (mod) {
            case 1:
            case 2:
            case 6:
            case 7:
                // Display the minute where we have just passed an increment of five
                sb.append(MessageFormat.format(Minute.JUST_AFTER.getLabel(locale), Minute.getMinute(roundToNearest5(minute)).getLabel(locale)));
                break;
            case 3:
            case 4:
            case 8:
            case 9:
                // Display the minute where we have yet to reach an increment of five
                sb.append(MessageFormat.format(Minute.JUST_BEFORE.getLabel(locale), Minute.getMinute(roundToNearest5(minute)).getLabel(locale)));
                break;
            case 0:
            case 5:
                // Display the minute where we have an increment of five i.e. five past ....
                sb.append(Minute.getMinute(minute).getLabel(locale));
                break;
            default:
                break;
        }

        final String formatted;
        final SimpleDateFormat df = new SimpleDateFormat("HH");
        final Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);

        // Display the hour, if we are greater than 37 minutes then increment the hour as the context has changed
        if (minute < 35) {

            formatted = MessageFormat.format(sb.toString(),Hour.getHour(df.format(calendar.getTime())).getLabel(locale));

        } else {

            calendar.add(Calendar.HOUR_OF_DAY, 1);
            formatted = MessageFormat.format(sb.toString(),Hour.getHour(df.format(calendar.getTime())).getLabel(locale));

        }

        return formatted;
    }

    /**
     * Round to the nearest 5 using common algebra.
     * 
     * @param minute number to be rounded
     * @return the rounded number
     */
    protected static int roundToNearest5(final int minute) {
        // Add half the number you're trying to roundToNearest5 (5). Divide to remove the decimals then multiply by 5.
        return ((minute + (5 / 2)) / 5) * 5;
    }
}
