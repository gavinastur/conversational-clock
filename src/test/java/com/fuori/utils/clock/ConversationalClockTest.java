package com.fuori.utils.clock;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Test Case to test the Conventional Clock.
 * 
 * @author gavinastur May 20, 2012 09:02:19 AM
 */
public class ConversationalClockTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    /**
     * Default Test Case for testing the time on the hour.
     * 00:00 = "midnight"
     */
	@Test
    public final void testGetTimeOnTheHour() {
        Assert.assertEquals("midnight", ConversationalClock.getTime(0, 0));
    }

    /**
     * Default Test Case for testing just after....
     *  00:01 = "just after midnight"
     *  
     */
	@Test
    public final void testGetTimeJustAfter() {
        Assert.assertEquals("just after midnight", ConversationalClock.getTime(0, 1));
    }

    /**
     * Default Test Case for testing just before....
     * 00:04 = "just before five past midnight"
     */
	@Test
    public final void testGetTimeJustBefore() {
        Assert.assertEquals("just before five past midnight", ConversationalClock.getTime(0, 4));
    }

    /**
     * Default Test Case for testing five past....
     *  00:05 = "five past eight am"
     */
	@Test
    public final void testGetTimeFivePast() {
        Assert.assertEquals("five past eight am", ConversationalClock.getTime(8, 5));
    }

    /**
     * Default Test Case for testing half past....
     * 23:30 = "half past eleven pm"
     *  
     */
	@Test
    public final void testGetTimeHalfPast() {
        Assert.assertEquals("half past eleven pm", ConversationalClock.getTime(23, 30));
    }

    /**
     * Default Test Case for testing twenty to....
     * 23:40 = "twenty to midnight"
     *
     */
	@Test
    public final void testGetTimeTwentyTo() {
        Assert.assertEquals("twenty to midnight", ConversationalClock.getTime(23, 40));
    }

    /**
     * Default Test Case for testing twenty five to....
     * 23:35 = "twenty five to midnight"
     *
     */
	@Test
    public final void testGetTimeTwentyFiveTo() {
        Assert.assertEquals("twenty five to midnight", ConversationalClock.getTime(23, 35));
    }

    @Test
    public final void testGetTimeFromMillis() {
        final long millis = 1405668575844L;
        Assert.assertEquals("just before half past seven am", ConversationalClock.getTime(millis));
    }

    @Test
    public final void testRound() {
        Assert.assertEquals(10, ConversationalClock.roundToNearest5(9));
    }

    @Test
    public final void testInvalidHourMin() {
        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("-1:0 is not valid. hourOfDay must be 0-23. minute must be 0-59");

        ConversationalClock.getTime(-1, 0);
    }

    @Test
    public final void testInvalidMinuteMin() {

        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("0:-1 is not valid. hourOfDay must be 0-23. minute must be 0-59");

        ConversationalClock.getTime(0, -1);
    }

    @Test
    public final void testInvalidHourMax() {

        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("24:0 is not valid. hourOfDay must be 0-23. minute must be 0-59");

        ConversationalClock.getTime(24, 0);
    }

    @Test
    public final void testInvalidMinuteMax() {

        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("0:60 is not valid. hourOfDay must be 0-23. minute must be 0-59");

        ConversationalClock.getTime(0, 60);
    }



}
