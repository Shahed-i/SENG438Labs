package org.jfree.data;

import static org.junit.Assert.*; 
import org.jfree.data.Range; 
import org.junit.*;

public class RangeTest {
	private Range testrange;
	private Range invrange;
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
    public void testExpandToIncludeNullRange() {
    	Range result = Range.expandToInclude(null, 5.0);
        assertEquals("Result range should be 5.0 to 5.0", result.getLowerBound(), result.getUpperBound(), 0.0001);
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

    // <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
    // Lab 3 CODE HERE ON UNTIL @AFTER PARAMETER METHOD
    // <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
    
    //Constructor Test
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorBiggerLowerBound() {
    	testrange = new Range(3,2);
    }
    
    //Central Value Test
    @Test
    public void testGetCentralValue() {
    	double cenval = new Range(4,8).getCentralValue();
    	assertEquals("The Central Value should be 6", 6.0, cenval, 0.0001);
    }
    
    //Contains Tests
    @Test
    public void testContainsValueInsideRange() {
        Range range = new Range(5.0, 10.0);
        assertTrue("Value 7.0 should be within the range", range.contains(7.0));
    }
    
    @Test
    public void testContainsValueOutsideRangeLower() {
        Range range = new Range(5.0, 10.0);
        assertFalse("Value 4.9 should be outside the range", range.contains(4.9));
    }
    
    @Test
    public void testContainsValueOutsideRangeHigher() {
        Range range = new Range(5.0, 10.0);
        assertFalse("Value 10.1 should be outside the range", range.contains(10.1));
    }
    
    //Intersects Tests
    @Test
    public void testIntersectsDoubleValues_LowB0HighB1Lower() {
        Range range = new Range(5.0, 10.0);
        assertTrue("Should return true when b0 is less than or equal to lower and b1 is greater than lower", range.intersects(4.0, 6.0));
    }
    
    @Test
    public void testIntersectsDoubleValues_LowB0LowB1Lower() {
        Range range = new Range(5.0, 10.0);
        assertFalse("Should return false when b0 is less than or equal to lower and b1 is lower than lower", range.intersects(4.0, 4.9));
    }
    
    @Test
    public void testIntersectsDoubleValues_HighB0HighB1Upper() {
        Range range = new Range(5.0, 10.0);
        assertTrue("Should return true when b0 is higher than lower and lower than upper and b1 is higher than b0", range.intersects(6.0, 7.0));
    }
    
    @Test
    public void testIntersectsDoubleValues_HighB0LowB1Upper() {
        Range range = new Range(5.0, 10.0);
        assertFalse("Should return false when b0 is higher than lower and lower than upper and b1 is lower than b0", range.intersects(6.0, 5.0));
    }
    
    @Test
    public void testIntersectsDoubleValues_HigherB0HighB1Upper() {
        Range range = new Range(5.0, 10.0);
        assertFalse("Should return false when b0 is higher than lower and upper and b1 is higher than b0", range.intersects(11.0, 12.0));
    }
    
    @Test
    public void testIntersetcsRangeValue() {
    	Range range1 = new Range(5.0, 10.0);
    	Range range2 = new Range(6.0, 7.0);
    	assertTrue("Should return true as range1 intersects range2", range1.intersects(range2));
    }
    
    //Constrain Tests
    @Test
    public void testConstrainUpper() {
    	Range range1 = new Range(5.0, 10.0);
    	assertEquals("Should return 10.0", 10.0, range1.constrain(12.0), 0.0001);
    }
    
    @Test
    public void testConstrainLower() {
    	Range range1 = new Range(5.0, 10.0);
    	assertEquals("Should return 5.0", 5.0, range1.constrain(4.0), 0.0001);
    }
    
    @Test
    public void testConstraintInRange() {
    	Range range1 = new Range(5.0, 10.0);
    	assertEquals("Should return 6.0", 6.0, range1.constrain(6.0), 0.0001);
    }
    
    //Combine Range Tests
    @Test
    public void testCombineBothRangesNonNull() {
        Range range1 = new Range(5.0, 10.0);
        Range range2 = new Range(8.0, 15.0);

        Range result = new Range(5.0, 15.0);
        
        assertTrue("Combined range should be from 5.0 to 15.0", result.equals(Range.combine(range1, range2)));
    }
    
    @Test
    public void testCombineNullRange1() {
        Range range2 = new Range(5.0, 10.0);
        Range result = new Range(5.0, 10.0);
        assertTrue("Combined range should be the same", result.equals(Range.combine(null, range2)));   
    }
    
    @Test
    public void testCombineNullRange2() {
        Range range1 = new Range(5.0, 10.0);
        Range result = new Range(5.0, 10.0);
        assertTrue("Combined range should be the same", result.equals(Range.combine(range1, null)));   
    }
    
    //CombineIgnoringNaN Tests
    @Test
    public void testCombineIgnoringNaN_BothNaNRanges() {
        Range range1 = new Range(Double.NaN, Double.NaN);
        Range range2 = new Range(Double.NaN, Double.NaN);

        Range result = Range.combineIgnoringNaN(range1, range2);

        assertNull("Result should be null when both lower and upper bounds are NaN", result);
    }
    
    @Test
    public void testCombineIgnoringNaN_BothNaNUpperRanges() {
        Range range1 = new Range(5.0, Double.NaN);
        Range range2 = new Range(6.0, Double.NaN);

        Range result = Range.combineIgnoringNaN(range1, range2);
        
        assertEquals("Lower bound should be 5.0", 5.0, result.getLowerBound(), 0.0001);
        assertTrue("Upper bound should be NaN", Double.isNaN(result.getUpperBound()));
    }
    
    @Test
    public void testCombineIgnoringNaN_BothNaNLowerRanges() {
        Range range1 = new Range(Double.NaN, 5.0);
        Range range2 = new Range(Double.NaN, 6.0);

        Range result = Range.combineIgnoringNaN(range1, range2);
        
        assertEquals("Upper bound should be 6.0", 6.0, result.getUpperBound(), 0.0001);
        assertTrue("Lower bound should be NaN", Double.isNaN(result.getLowerBound()));
    }
    
    @Test
    public void testCombineIgnoringNaN_NullRange1NaNRange2() {
        Range range2 = new Range(Double.NaN, Double.NaN);

        Range result = Range.combineIgnoringNaN(null, range2);
        
        assertNull("Result should be null", result);
    }
    
    @Test
    public void testCombineIgnoringNaN_NullRange1NormalRange2() {
        Range range2 = new Range(5.0, 10.0);

        Range result = Range.combineIgnoringNaN(null, range2);
        
        assertTrue("Combined range should be the same", result.equals(range2)); 
    }
    
    @Test
    public void testCombineIgnoringNaN_NaNRange1NullRange2() {
        Range range1 = new Range(Double.NaN, Double.NaN);

        Range result = Range.combineIgnoringNaN(range1, null);
        
        assertNull("Result should be null", result);
    }
    
    @Test
    public void testCombineIgnoringNaN_NormalRange1NullRange2() {
        Range range1 = new Range(5.0, 10.0);

        Range result = Range.combineIgnoringNaN(range1, null);
        
        assertTrue("Combined range should be the same", result.equals(range1)); 
    }
    
    @Test
    public void testCombineIgnoringNaN_NullRange1NullRange2() {
        Range result = Range.combineIgnoringNaN(null, null);
        
        assertNull("Result should be null", result);
    }
    
    //minimum and max test
    @Test
    public void testNanMin() {
        Range range1 = new Range(4.0, 5.0);
        Range range2 = new Range(Double.NaN, 6.0);

        Range result = Range.combineIgnoringNaN(range1, range2);
        assertEquals("Minimum of result lower should be 4.0", 4.0, result.getLowerBound(), 0.0001);
    }
    
    @Test
    public void testNanMax() {
        Range range1 = new Range(5.0, 10.0);
        Range range2 = new Range(6.0, Double.NaN);

        Range result = Range.combineIgnoringNaN(range1, range2);
        assertEquals("Maximum of result upper should be 10.0", 10.0, result.getUpperBound(), 0.0001);
    }
    
    //Expand Third Condition Test
    @Test
    public void testNegativeUpperMargin() {
        Range range = new Range(10.0, 20.0);
        Range result = Range.expand(range, -1.5, -1.5);
        testrange = new Range(15.0, 15.0);

        assertTrue("Lower bound should be adjusted", testrange.equals(result));
    }
    
    //Shift Tests
    @Test
    public void testShiftTwoParamsNegativeLowPositiveUpper() {
    	Range range1 = new Range(-1.0, 1.0);
    	Range range2 = new Range(0.0, 100.0);
    	
    	Range result = Range.shift(range1, 99.0);
    	assertTrue("The new range should be from 0 to 100", range2.equals(result));
    }
    
    @Test
    public void testShiftTwoParamsZeroRangeBound() {
    	Range range1 = new Range(0.0, 1.0);
    	Range range2 = new Range(99.0, 100.0);
    	
    	Range result = Range.shift(range1, 99.0);
    	assertTrue("The new range should be from 99 to 100", range2.equals(result));
    }
    
    @Test
    public void testShiftThreeParamsNegativeLowerBound() {
    	Range range1 = new Range(-49.0, 1.0);
    	Range range2 = new Range(50.0, 100.0);
    	
    	Range result = Range.shift(range1, 99.0, true);
    	assertTrue("The new range should be from 50 to 100", range2.equals(result));
    }
    
    //Scale Tests
    @Test
    public void testValidScaling() {
    	Range range1 = new Range(1.0, 2.0);
    	Range range2 = new Range(2.0, 4.0);
    	
    	Range result = Range.scale(range1, 2.0);
    	assertTrue("The scaled range should be from 2 to 4", range2.equals(result));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidScaling() {
    	Range range1 = new Range(1.0, 2.0);
    	Range.scale(range1, -2.0);
    }
     
    //isNaNRange Condition Coverage Test
    @Test
    public void testIsNaNRangeValidUpper() {
    	Range range1 = new Range(Double.NaN, 5.0);
    	assertFalse("This result should be False, is not a NaNRange", range1.isNaNRange());
    }
    
    //Equals Test for Third Condition
    @Test
    public void testEqualsThirdIfCondition() {
    	Range range1 = new Range(5.0, Double.NaN);

        invrange = new Range(5.0, Double.NaN);

        assertFalse("Result should be False", range1.equals(invrange));
    }
    
    //Hash Code Coverage Test
    @Test
    public void testHashCode_ConsistentAndEqualRanges() {
        Range range1 = new Range(5.0, 10.0);
        Range range2 = new Range(5.0, 10.0);
        Range differentRange = new Range(6.0, 12.0);

        assertEquals("Hash codes of equal ranges should be equal", range1.hashCode(), range2.hashCode());

        assertNotEquals("Hash codes of different ranges should be different", range1.hashCode(), differentRange.hashCode());
    }
    
    //to a String Test
    @Test
    public void testToStringNormalRange() {
        Range range = new Range(5.0, 10.0);
        assertEquals("toString() should return 'Range[5.0,10.0]'", 
                     "Range[5.0,10.0]", range.toString());
    }
    
    // <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
    // Lab 3 END OF CODE
    // <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
    
    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
