package com.mycompany.app;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    @org.junit.Test
    public void testIsStringStrongShouldBeStrong() {
        ArrayList<Integer> arr1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ArrayList<Integer> arr2 = new ArrayList<>(Arrays.asList(-1, -2, -3, -4));
        ArrayList<Integer> arr3 = new ArrayList<>(Arrays.asList(2, 2, 47, 2));
        String str = "AAA";
        assertEquals(new App().isStringStrong(arr1, arr2, arr3, str), "Strong string!");

    }

    @org.junit.Test
    public void testIsStringStrongShouldBeWeak() {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(234, 2, 3, 4));
        assertEquals(new App().isStringStrong(arr, arr, arr, "A"), "Weak string!");
    }

    @org.junit.Test
    public void testIsStringStrongNullParam1() {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, 234, 55));
        assertEquals(new App().isStringStrong(null, arr, arr, "sdf"), "All forms must be filled!");
    }

    @org.junit.Test
    public void testIsStringStrongEmptyParam2() {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, 234, 55));
        ArrayList<Integer> emptyArr = new ArrayList<>();
        assertEquals(new App().isStringStrong(arr, emptyArr, arr, "sdf"), "All forms must be filled!");
    }

    @org.junit.Test
    public void testIsStringStrongNullParam3() {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, 234, 55));
        assertEquals(new App().isStringStrong(arr, arr, null, "sdf"), "All forms must be filled!");
    }

    @org.junit.Test
    public void testIsStringStrongEmptyParam4() {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, 234, 55));
        assertEquals(new App().isStringStrong(arr, arr, arr, ""), "All forms must be filled!");
    }

    @org.junit.Test
    public void testFindMaxOfList() {
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(1, 234, 55, 42, -12));
        assertEquals(new App().findMaxOfList(arr), 234);
    }

    @org.junit.Test(expected = NullPointerException.class)
    public void testFindMaxOfListNullParam() {
        try {
            new App().findMaxOfList(null);
        } catch (NullPointerException e) {
            assertTrue(true);
        }
    }
}
