package org.jfree.data.test;

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

        assertFalse("Hash codes of different ranges should be different", range1.hashCode() == differentRange.hashCode());
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
    
    // <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
    // Lab 4 CODE HERE ON UNTIL @AFTER PARAMETER METHOD
    // <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
   
    @Test
    public void testToString_крайниеValues() {
        Range range = new Range(-Double.MAX_VALUE, Double.MAX_VALUE);
        assertEquals("Range[-1.7976931348623157E308,1.7976931348623157E308]", range.toString());
    }

    @Test
    public void testToString_specialCharacters() {
        Range range = new Range(5.5, 10.1);
        assertEquals("Range[5.5,10.1]", range.toString());
    }

    @Test
    public void testContains_boundaryValues() {
        Range range = new Range(5.0, 10.0);
        assertTrue(range.contains(5.0));
        assertTrue(range.contains(10.0));
        assertFalse(range.contains(4.9999));
        assertFalse(range.contains(10.0001));
    }

    @Test
    public void testContains_NaN() {
        Range range = new Range(5.0, 10.0);
        assertFalse(range.contains(Double.NaN));
    }


    @Test
    public void testIntersects_nonIntersectingRanges() {
        Range range1 = new Range(5.0, 10.0);
        Range range2 = new Range(11.0, 15.0);
        assertFalse(range1.intersects(range2));
    }

   @Test
    public void testIntersects_invertedRange() {
        Range range1 = new Range(5.0, 10.0);
        assertFalse(range1.intersects(10.0, 5.0));
    }

    @Test
    public void testCombineIgnoringNaN_oneNaNRange() {
        Range range1 = new Range(5.0, 10.0);
        Range range2 = new Range(Double.NaN, Double.NaN);
        Range combined = Range.combineIgnoringNaN(range1, range2);
        assertEquals(range1, combined);
    }

    @Test
    public void testCombineIgnoringNaN_oneNaNLower() {
        Range range1 = new Range(5.0, 10.0);
        Range range2 = new Range(Double.NaN, 8.0);
        Range combined = Range.combineIgnoringNaN(range1, range2);
        assertEquals(5.0, combined.getLowerBound(), 0.0001);
        assertEquals(10.0, combined.getUpperBound(), 0.0001);
    }

    @Test
    public void testCombineIgnoringNaN_oneNaNUpper() {
        Range range1 = new Range(5.0, 10.0);
        Range range2 = new Range(7.0, Double.NaN);
        Range combined = Range.combineIgnoringNaN(range1, range2);
        assertEquals(5.0, combined.getLowerBound(), 0.0001);
        assertEquals(10.0, combined.getUpperBound(), 0.0001);
    }
    
    @Test
    public void testConstrainAtLowerBoundary() {
        Range range = new Range(5.0, 10.0);
        // When input equals lower bound, constrain should return the lower bound.
        assertEquals("Constrain should return lower bound when input equals lower", 
                     5.0, range.constrain(5.0), 0.0001);
    }

    @Test
    public void testConstrainAtUpperBoundary() {
        Range range = new Range(5.0, 10.0);
        // When input equals upper bound, constrain should return the upper bound.
        assertEquals("Constrain should return upper bound when input equals upper", 
                     10.0, range.constrain(10.0), 0.0001);
    }
    
    // Test shifting by zero (should return an equivalent range).
    @Test
    public void testShiftZero() {
        Range range = new Range(5.0, 10.0);
        Range shifted = Range.shift(range, 0.0);
        assertTrue("Shifting by zero should return the same range", range.equals(shifted));
    }
    
    // Test scaling by 1.0 (should return an equivalent range).
    @Test
    public void testScaleByOne() {
        Range range = new Range(5.0, 10.0);
        Range scaled = Range.scale(range, 1.0);
        assertTrue("Scaling by 1 should return the same range", range.equals(scaled));
    }
    
    // Test intersects when one endpoint of the interval exactly equals a range boundary.
    @Test
    public void testIntersectsOnBoundary() {
        Range range = new Range(5.0, 10.0);
        // When the interval touches the range at the lower boundary.
        assertTrue("Intersects should return true when interval starts at lower bound",
                   range.intersects(5.0, 7.0));
        // When the interval touches the range at the upper boundary.
        assertTrue("Intersects should return true when interval ends at upper bound",
                   range.intersects(7.0, 10.0));
    }
    
    // Test equals for ranges with NaN lower bounds.
    @Test
    public void testEqualsWithNaNLower() {
        Range range1 = new Range(Double.NaN, 10.0);
        Range range2 = new Range(Double.NaN, 10.0);
        // Depending on the implementation, ranges with NaN in the lower bound might not be considered equal.
        assertFalse("Ranges with NaN lower bound should not be equal", range1.equals(range2));
    }
    
    // Test hashCode for negative-valued ranges.
    @Test
    public void testHashCodeForNegativeRange() {
        Range range1 = new Range(-10.0, -5.0);
        Range range2 = new Range(-10.0, -5.0);
        assertEquals("Hash codes for identical negative ranges should be equal", 
                     range1.hashCode(), range2.hashCode());
    }
    
    // Test toString formatting for a zero-length range.
    @Test
    public void testToStringZeroLength() {
        Range range = new Range(0.0, 0.0);
        assertEquals("toString should format correctly for zero-length range", 
                     "Range[0.0,0.0]", range.toString());
    }
    
    // Test expand with fractional margins to kill mutants in the expansion logic.
    @Test
    public void testExpandWithFractionalMargins() {
        Range range = new Range(10.0, 20.0);
        // Expected: lower bound = 10 - 0.25*(20-10)= 7.5; upper bound = 20 + 0.25*(20-10)= 22.5
        Range expanded = Range.expand(range, 0.25, 0.25);
        assertEquals("Lower bound after expansion should be 7.5", 7.5, expanded.getLowerBound(), 0.0001);
        assertEquals("Upper bound after expansion should be 22.5", 22.5, expanded.getUpperBound(), 0.0001);
    }
    
    // Test expandToInclude when the value is exactly equal to current lower bound.
    @Test
    public void testExpandToIncludeValueEqualToLower() {
        Range range = new Range(5.0, 15.0);
        Range expanded = Range.expandToInclude(range, 5.0);
        assertEquals("Range should remain unchanged if value equals lower bound", range, expanded);
    }
    
    // Test expandToInclude when the value is exactly equal to current upper bound.
    @Test
    public void testExpandToIncludeValueEqualToUpper() {
        Range range = new Range(5.0, 15.0);
        Range expanded = Range.expandToInclude(range, 15.0);
        assertEquals("Range should remain unchanged if value equals upper bound", range, expanded);
    }
    
    // Test combine for non-overlapping ranges (should combine to the full span).
    @Test
    public void testCombineNonOverlappingRanges() {
        Range range1 = new Range(1.0, 5.0);
        Range range2 = new Range(10.0, 15.0);
        Range combined = Range.combine(range1, range2);
        Range expected = new Range(1.0, 15.0);
        assertEquals("Combined non-overlapping ranges should equal [1.0,15.0]", expected, combined);
    }
    
    @Test
    public void testConstrainWithNaN() {
        Range range = new Range(5.0, 15.0);
        assertTrue("Constraining NaN should return NaN", Double.isNaN(range.constrain(Double.NaN)));
    }

    @Test
    public void testConstrainFarOutOfBounds() {
        Range range = new Range(5.0, 15.0);
        assertEquals("Constraining a very large negative number should return lower bound", 
                     5.0, range.constrain(-1000.0), 0.0001);
        assertEquals("Constraining a very large positive number should return upper bound", 
                     15.0, range.constrain(1000.0), 0.0001);
    }

    @Test
    public void testShiftWithAllowingZeroCrossing() {
        Range range = new Range(-10.0, 10.0);
        Range shifted = Range.shift(range, -5.0, true);
        assertEquals("Lower bound should shift correctly", -15.0, shifted.getLowerBound(), 0.0001);
        assertEquals("Upper bound should shift correctly", 5.0, shifted.getUpperBound(), 0.0001);
    }

    @Test
    public void testEqualsWithExtremeValues() {
        Range range1 = new Range(Double.MIN_VALUE, Double.MAX_VALUE);
        Range range2 = new Range(Double.MIN_VALUE, Double.MAX_VALUE);
        assertTrue("Ranges with extreme values should be equal", range1.equals(range2));
    }

    @Test
    public void testIntersectsWithIdenticalEndpoints() {
        Range range = new Range(5.0, 15.0);
        assertTrue("Intersects should return true when both endpoints are exactly at the range bounds",
                   range.intersects(5.0, 15.0));
    }

    @Test
    public void testExpandLargeRange() {
        Range range = new Range(-1.0E9, 1.0E9);
        Range expanded = Range.expand(range, 0.5, 0.5);
        assertEquals("Lower bound should expand correctly", -2.0E9, expanded.getLowerBound(), 1.0);
        assertEquals("Upper bound should expand correctly", 2.0E9, expanded.getUpperBound(), 1.0);
    }

    @Test
    public void testCombineWithNull() {
        Range range = new Range(5.0, 10.0);
        Range combined = Range.combine(null, range);
        assertEquals("Combining with null should return the non-null range", range, combined);
    }

    @Test
    public void testCombineIgnoringNaNWithBothNaN() {
        Range range1 = new Range(Double.NaN, Double.NaN);
        Range range2 = new Range(Double.NaN, Double.NaN);
        assertNull("Combining two NaN ranges should return null", Range.combineIgnoringNaN(range1, range2));
    }

    @Test
    public void testCombineIgnoringNaNWithOneNaN() {
        Range validRange = new Range(10.0, 20.0);
        Range rangeWithNaN = new Range(Double.NaN, Double.NaN);
        Range result = Range.combineIgnoringNaN(validRange, rangeWithNaN);
        assertEquals("Combining a NaN range with a valid range should return the valid range",
                     validRange, result);
    }

    @Test
    public void testExpandToIncludeWhenNull() {
        Range expanded = Range.expandToInclude(null, 10.0);
        assertEquals("Expanding a null range should create a new single-point range",
                     new Range(10.0, 10.0), expanded);
    }

    @Test
    public void testHashCodeConsistency() {
        Range range = new Range(5.0, 10.0);
        int initialHashCode = range.hashCode();
        assertEquals("Hash code should be consistent on multiple calls", initialHashCode, range.hashCode());
    }

    @Test
    public void testToStringFormat() {
        Range range = new Range(-5.5, 8.3);
        assertEquals("toString should format correctly", "Range[-5.5,8.3]", range.toString());
    }

    @Test
    public void testScaleByZero() {
        Range range = new Range(5.0, 10.0);
        Range scaled = Range.scale(range, 0.0);
        assertEquals("Scaling by zero should return a range at zero", new Range(0.0, 0.0), scaled);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testScaleByNegativeValue() {
        Range range = new Range(5.0, 10.0);
        Range.scale(range, -2.0);
    }

    @Test
    public void testInvalidRange() {
        try {
            new Range(10.0, 5.0);
            fail("Creating a range where upper bound < lower bound should throw an exception");
        } catch (IllegalArgumentException expected) {
            // Test passes
        }
    }
    
    @Test
    public void testExpandZeroExpansion() {
        Range range = new Range(5.0, 10.0);
        Range expanded = Range.expand(range, 0, 0);
        assertEquals("Expanded lower bound should remain 5.0", 5.0, expanded.getLowerBound(), 0.0001);
        assertEquals("Expanded upper bound should remain 10.0", 10.0, expanded.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandNegativeExpansion() {
        Range range = new Range(5.0, 10.0);
        Range expanded = Range.expand(range, -0.5, -0.5);
        assertEquals("Lower bound should be 7.5 after negative expansion", 7.5, expanded.getLowerBound(), 0.0001);
        assertEquals("Upper bound should be 7.5 after negative expansion", 7.5, expanded.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandEdgeCase() {
        Range range = new Range(0.0, 1.0);
        Range expanded = Range.expand(range, 1.0, 1.0);
        assertEquals("Lower bound should be -1.0", -1.0, expanded.getLowerBound(), 0.0001);
        assertEquals("Upper bound should be 2.0", 2.0, expanded.getUpperBound(), 0.0001);
    }

    @Test
    public void testConstrainOnLowerBound() {
        Range range = new Range(5.0, 10.0);
        assertEquals("Constraining at lower bound should return 5.0", 5.0, range.constrain(5.0), 0.0001);
    }

    @Test
    public void testConstrainOnUpperBound() {
        Range range = new Range(5.0, 10.0);
        assertEquals("Constraining at upper bound should return 10.0", 10.0, range.constrain(10.0), 0.0001);
    }

    @Test
    public void testConstrainBelowLowerBound() {
        Range range = new Range(5.0, 10.0);
        assertEquals("Constraining below lower bound should return 5.0", 5.0, range.constrain(4.0), 0.0001);
    }

    @Test
    public void testConstrainAboveUpperBound() {
        Range range = new Range(5.0, 10.0);
        assertEquals("Constraining above upper bound should return 10.0", 10.0, range.constrain(11.0), 0.0001);
    }

    @Test
    public void testEqualsWithDifferentLowerBound() {
        Range range1 = new Range(5.0, 10.0);
        Range range2 = new Range(6.0, 10.0);
        assertFalse("Ranges with different lower bounds should not be equal", range1.equals(range2));
    }

    @Test
    public void testEqualsWithDifferentUpperBound() {
        Range range1 = new Range(5.0, 10.0);
        Range range2 = new Range(5.0, 11.0);
        assertFalse("Ranges with different upper bounds should not be equal", range1.equals(range2));
    }

    @Test
    public void testShiftWithoutAllowingZeroCrossing() {
        Range range = new Range(-5.0, 5.0);
        Range shifted = Range.shift(range, 10.0, false);
        assertEquals("Lower bound should be 0 after shift", 0.0, shifted.getLowerBound(), 0.0001);
        assertEquals("Upper bound should be 15.0 after shift", 15.0, shifted.getUpperBound(), 0.0001);
    }

    @Test
    public void testShiftAllowingZeroCrossing() {
        Range range = new Range(-5.0, 5.0);
        Range shifted = Range.shift(range, 10.0, true);
        assertEquals("Lower bound should be 5.0 after shift", 5.0, shifted.getLowerBound(), 0.0001);
        assertEquals("Upper bound should be 15.0 after shift", 15.0, shifted.getUpperBound(), 0.0001);
    }

    @Test
    public void testCombineWithOverlappingRanges() {
        Range range1 = new Range(5.0, 15.0);
        Range range2 = new Range(10.0, 20.0);
        Range combined = Range.combine(range1, range2);
        assertEquals("Lower bound should be 5.0", 5.0, combined.getLowerBound(), 0.0001);
        assertEquals("Upper bound should be 20.0", 20.0, combined.getUpperBound(), 0.0001);
    }

    @Test
    public void testCombineWithNonOverlappingRanges() {
        Range range1 = new Range(5.0, 10.0);
        Range range2 = new Range(15.0, 20.0);
        Range combined = Range.combine(range1, range2);
        assertEquals("Lower bound should be 5.0", 5.0, combined.getLowerBound(), 0.0001);
        assertEquals("Upper bound should be 20.0", 20.0, combined.getUpperBound(), 0.0001);
    }

    @Test
    public void testCombineIgnoringNaNWithNaNValues() {
        Range range1 = new Range(Double.NaN, Double.NaN);
        Range range2 = new Range(5.0, 10.0);
        Range combined = Range.combineIgnoringNaN(range1, range2);
        assertEquals("Lower bound should be 5.0", 5.0, combined.getLowerBound(), 0.0001);
        assertEquals("Upper bound should be 10.0", 10.0, combined.getUpperBound(), 0.0001);
    }

    @Test
    public void testCombineIgnoringNaNWithOnlyNaNRange() {
        Range range1 = new Range(Double.NaN, Double.NaN);
        Range range2 = new Range(Double.NaN, Double.NaN);
        Range combined = Range.combineIgnoringNaN(range1, range2);
        assertNull("Result should be null when both ranges are NaN", combined);
    }
    
    @Test
    public void testShiftZeroDistance() {
        Range range = new Range(5.0, 10.0);
        Range shifted = Range.shift(range, 0.0);
        assertEquals("Lower bound should remain 5.0", 5.0, shifted.getLowerBound(), 0.0001);
        assertEquals("Upper bound should remain 10.0", 10.0, shifted.getUpperBound(), 0.0001);
    }

    @Test
    public void testShiftNegativeDistance() {
        Range range = new Range(5.0, 10.0);
        Range shifted = Range.shift(range, -2.0);
        assertEquals("Lower bound should be 3.0", 3.0, shifted.getLowerBound(), 0.0001);
        assertEquals("Upper bound should be 8.0", 8.0, shifted.getUpperBound(), 0.0001);
    }

    @Test
    public void testExpandToNegativeBounds() {
        Range range = new Range(5.0, 10.0);
        Range expanded = Range.expand(range, -2.0, -2.0);
        assertEquals("Lower bound should be 7.5", 7.5, expanded.getLowerBound(), 0.0001);
        assertEquals("Upper bound should be 7.5", 7.5, expanded.getUpperBound(), 0.0001);
    }

    @Test
    public void testEqualsWithNull() {
        Range range = new Range(5.0, 10.0);
        assertFalse("Range should not be equal to null", range.equals(null));
    }

    @Test
    public void testHashCodeConsistency1() {
        Range range = new Range(5.0, 10.0);
        assertEquals("HashCode should be consistent", range.hashCode(), range.hashCode());
    }

    @Test
    public void testCombineWithNullFirst() {
        Range range = new Range(5.0, 10.0);
        assertEquals("Combining null with a range should return the range", range, Range.combine(null, range));
    }

    @Test
    public void testCombineWithNullSecond() {
        Range range = new Range(5.0, 10.0);
        assertEquals("Combining a range with null should return the range", range, Range.combine(range, null));
    }

    @Test
    public void testCombineWithSameRange() {
        Range range = new Range(5.0, 10.0);
        assertEquals("Combining a range with itself should return the same range", range, Range.combine(range, range));
    }

    @Test
    public void testConstrainInsideRange() {
        Range range = new Range(5.0, 10.0);
        assertEquals("Constraining inside range should return the same value", 7.0, range.constrain(7.0), 0.0001);
    }

    @Test
    public void testToStringFormat1() {
        Range range = new Range(5.0, 10.0);
        assertEquals("Range should format correctly", "Range[5.0,10.0]", range.toString());
    }
 // <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
    // Lab 4 END OF CODE
    // <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
    
    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
