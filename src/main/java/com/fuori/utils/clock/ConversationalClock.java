package com.fuori.utils.clock;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    public static String getCurrentTime() {

        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);

        return getTime(hour, minute);
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

        return getTime(hour, minute);
    }

    /**
     * Method to to get the time in polite text. e.g. 00:05 is "five past midnight".
     * 
     * @param hourOfDay The hour you wish to convert
     * @param minute The minute you wish to convert
     * @return String formatted as text e.g. "five past midnight"
     */
    public static String getTime(final int hourOfDay, final int minute) {

        if ((hourOfDay > 23 || hourOfDay < 0) || (minute > 59 || minute < 0))  {
            throw new RuntimeException(hourOfDay + ":" + minute + " is not valid. hourOfDay must be 0-23. minute must be 0-59.");
        }

        StringBuilder sb = new StringBuilder();

        int mod = minute % 10;

        switch (mod) {
            case 1:
            case 2:
            case 6:
            case 7:
                // Display the minute where we have just passed an increment of five
                sb.append(Minute.JUST_AFTER.getLabel()).append(Minute.getMinute(roundToNearest5(minute)).getLabel());
                break;
            case 3:
            case 4:
            case 8:
            case 9:
                // Display the minute where we have yet to reach an increment of five
                sb.append(Minute.JUST_BEFORE.getLabel()).append(Minute.getMinute(roundToNearest5(minute)).getLabel());
                break;
            case 0:
            case 5:
                // Display the minute where we have an increment of five i.e. five past ....
                sb.append(Minute.getMinute(minute).getLabel());
                break;
            default:
                break;
        }

        SimpleDateFormat df = new SimpleDateFormat("HH");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);

        // Display the hour, if we are greater than 37 minutes then increment the hour as the context has changed
        if (minute < 35) {
            sb.append(Hour.getHour(df.format(calendar.getTime())).getLabel());
        } else {
            calendar.add(Calendar.HOUR_OF_DAY, 1);
            sb.append(Hour.getHour(df.format(calendar.getTime())).getLabel());
        }

        return sb.toString();
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
