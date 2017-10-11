package com.inabex.reminder;

import org.junit.Test;

import info.test.reminder.alarm.AlarmsManager;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void funIsCorrect() throws Exception {
        int test = AlarmsManager.getTestValue(8);

        int expected = 8*3;
        assertEquals("Test failed", expected, test);
    }


}