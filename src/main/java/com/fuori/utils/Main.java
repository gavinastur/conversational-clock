package com.fuori.utils;

import com.fuori.utils.clock.ConversationalClock;

import java.util.Calendar;
import java.util.Locale;

/**
 * 
 * @author gavinastur May 19, 2012 10:21:16 PM
 */
public class Main {

    /**
     * Private constructor.
     */
    private Main() {
    }

    /**
     * @param arguments Sting Array of parameters
     */
    public static void main(final String[] arguments) {

        // Default to displaying the current time
        if (arguments.length == 0) {

            System.out.println(ConversationalClock.getTime(System.currentTimeMillis()));

        } else {

            Calendar now = Calendar.getInstance();
            int hour = now.get(Calendar.HOUR_OF_DAY);

            for (int minute = 0; minute < 60; minute++) {

                System.out.println(hour + ":" + String.format("%02d", minute) + " " + ConversationalClock.getTime(hour, minute, Locale.ITALY));
            }

        }

    }
}
