package org.jfree.data.test;

import static org.junit.Assert.*; 
import org.jfree.data.Range; 
import org.junit.*;

public class RangeTest {
    @BeforeClass 
    public static void setUpBeforeClass() throws Exception {
    }


    @Before
    public void setUp() throws Exception { new Range(-1, 1);
    }


    @Test
    public void testEqualsSameRange() {
        Range range1 = new Range(0, 10);
        Range range2 = new Range(0, 10);
        assertTrue("Identical ranges should be equal", range1.equals(range2));
    }

    @Test
    public void testEqualsDifferentRange() {
        Range range1 = new Range(0, 10);
        Range range2 = new Range(5, 15);
        assertFalse("Different ranges should not be equal", range1.equals(range2));
    }

    @Test
    public void testEqualsNullObject() {
        Range range = new Range(0, 10);
        assertFalse("Range should not be equal to null", range.equals(null));
    }

    @Test
    public void testEqualsDifferentObjectType() {
        Range range = new Range(0, 10);
        String differentObject = "Not a Range";
        assertFalse("Range should not be equal to a different object type", range.equals(differentObject));
    }
    
    @Test
    public void testExpandPositiveRange() {
        Range range = new Range(10, 20);
        Range expanded = Range.expand(range, 0.1, 0.2); // Expands 10% lower, 20% upper
        assertEquals("Lower bound should be 9", 9, expanded.getLowerBound(), 0.0001);
        assertEquals("Upper bound should be 22", 22, expanded.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandNegativeRange() {
        Range range = new Range(-20, -10);
        Range expanded = Range.expand(range, 0.5, 0.5); // Expands 50% lower, 50% upper
        assertEquals("Lower bound should be -25", -25, expanded.getLowerBound(), 0.0001);
        assertEquals("Upper bound should be -5", -5, expanded.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandWithZeroMargins() {
        Range range = new Range(10, 20);
        Range expanded = Range.expand(range, 0, 0); // No change
        assertEquals("Lower bound should remain 10", 10, expanded.getLowerBound(), 0.0001);
        assertEquals("Upper bound should remain 20", 20, expanded.getUpperBound(), 0.0001);
    }
    
    @Test
    public void testExpandToIncludeInsideRange() {
        Range range = new Range(5, 15);
        Range expanded = Range.expandToInclude(range, 10); // Inside range
        assertEquals("Lower bound should remain 5", 5, expanded.getLowerBound(), 0.0001);
        assertEquals("Upper bound should remain 15", 15, expanded.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandToIncludeLower() {
        Range range = new Range(5, 15);
        Range expanded = Range.expandToInclude(range, 2); // Below lower bound
        assertEquals("Lower bound should become 2", 2, expanded.getLowerBound(), 0.0001);
        assertEquals("Upper bound should remain 15", 15, expanded.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandToIncludeUpper() {
        Range range = new Range(5, 15);
        Range expanded = Range.expandToInclude(range, 20); // Above upper bound
        assertEquals("Lower bound should remain 5", 5, expanded.getLowerBound(), 0.0001);
        assertEquals("Upper bound should become 20", 20, expanded.getUpperBound(), 0.0001);
    }

    @Test
    public void testGetLengthPositiveRange() {
        Range range = new Range(10, 20);
        assertEquals("Length should be 10", 10, range.getLength(), 0.0001);
    }

    @Test
    public void testGetLengthNegativeRange() {
        Range range = new Range(-10, 5);
        assertEquals("Length should be 15", 15, range.getLength(), 0.0001);
    }

    @Test
    public void testGetLengthZeroRange() {
        Range range = new Range(5, 5);
        assertEquals("Length should be 0", 0, range.getLength(), 0.0001);
    }
    
    @Test
    public void testGetLowerBoundPositiveRange() {
        Range range = new Range(10, 20);
        assertEquals("Lower bound should be 10", 10, range.getLowerBound(), 0.0001);
    }

    @Test
    public void testGetLowerBoundNegativeRange() {
        Range range = new Range(-15, -5);
        assertEquals("Lower bound should be -15", -15, range.getLowerBound(), 0.0001);
    }

    @Test
    public void testGetLowerBoundZero() {
        Range range = new Range(0, 10);
        assertEquals("Lower bound should be 0", 0, range.getLowerBound(), 0.0001);
    }

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
