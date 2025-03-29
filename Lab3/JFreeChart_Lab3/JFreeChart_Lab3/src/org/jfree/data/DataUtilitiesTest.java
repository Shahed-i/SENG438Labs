package org.jfree.data;

import static org.junit.Assert.*;
import org.junit.*;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jfree.data.DataUtilities;



public class DataUtilitiesTest extends DataUtilities {
    
    @Test
    public void testCalculateColumnTotal2Params_ValidData() {
        Mockery context = new Mockery();
        final Values2D mockData = context.mock(Values2D.class);
        
        context.checking(new Expectations() {{
            one(mockData).getRowCount(); will(returnValue(3));
            one(mockData).getValue(0, 1); will(returnValue(5.0));
            one(mockData).getValue(1, 1); will(returnValue(3.0));
            one(mockData).getValue(2, 1); will(returnValue(2.0));
        }});
        
        double result = DataUtilities.calculateColumnTotal(mockData, 1);
        assertEquals("Sum of column 1 should be 10.0", 10.0, result, 0.0001);
    }
    
    @Test
    public void testCalculateColumnTotal2Params_EmptyData() {
        Mockery context = new Mockery();
        Values2D mockData = context.mock(Values2D.class);
        
        context.checking(new Expectations() {{
            one(mockData).getRowCount(); will(returnValue(0));
        }});
        
        double result = DataUtilities.calculateColumnTotal(mockData, 1);
        assertEquals("Sum should be 0 for an empty table", 0.0, result, 0.0001);
    }
    
    @Test
    public void testCalculateColumnTotal2Params_NullValues() {
        Mockery context = new Mockery();
        Values2D mockData = context.mock(Values2D.class);
        
        context.checking(new Expectations() {{
            one(mockData).getRowCount(); will(returnValue(3));
            one(mockData).getValue(0, 1); will(returnValue(null));
            one(mockData).getValue(1, 1); will(returnValue(1.0));
            one(mockData).getValue(2, 1); will(returnValue(5.0));
        }});
        
        double result = DataUtilities.calculateColumnTotal(mockData, 1);
        assertEquals("Sum should only include non-null values", 6.0, result, 0.0001);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateColumnTotal2Params_NullData() {
        DataUtilities.calculateColumnTotal(null, 1);
    }
    @Test
    public void testCalculateRowTotal2Params_ValidData() {
        Mockery context = new Mockery();
        Values2D mockData = context.mock(Values2D.class);

        context.checking(new Expectations() {{
            one(mockData).getColumnCount(); will(returnValue(3)); // 3 columns
            one(mockData).getValue(1, 0); will(returnValue(4.0)); // Value at row 1, column 0
            one(mockData).getValue(1, 1); will(returnValue(2.0)); // Value at row 1, column 1
            one(mockData).getValue(1, 2); will(returnValue(3.0)); // Value at row 1, column 2
        }});

        double result = DataUtilities.calculateRowTotal(mockData, 1);
        assertEquals("Sum of row 1 should be 9.0", 9.0, result, 0.0001); // 4.0 + 2.0 + 3.0 = 9.0
    }
    
    @Test
    public void testCalculateRowTotal2Params_EmptyData() {
        Mockery context = new Mockery();
        Values2D mockData = context.mock(Values2D.class);
        
        context.checking(new Expectations() {{
            one(mockData).getColumnCount(); will(returnValue(0));
        }});
        
        double result = DataUtilities.calculateRowTotal(mockData, 1);
        assertEquals("Sum should be 0 for an empty table", 0.0, result, 0.0001);
    }
    
    @Test
    public void testCalculateRowTotal2Params_NullValues() {
        Mockery context = new Mockery();
        Values2D mockData = context.mock(Values2D.class);
        
        context.checking(new Expectations() {{
            one(mockData).getColumnCount(); will(returnValue(3));
            one(mockData).getValue(1, 0); will(returnValue(null));
            one(mockData).getValue(1, 1); will(returnValue(2.0));
            one(mockData).getValue(1, 2); will(returnValue(null));
        }});
        
        double result = DataUtilities.calculateRowTotal(mockData, 1);
        assertEquals("Sum should only include non-null values", 2.0, result, 0.0001);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testCalculateRowTotal2Params_NullData() {
        DataUtilities.calculateRowTotal(null, 1);
    }
    
    @Test
    public void testCreateNumberArray_ValidData() {
        double[] input = {1.0, 2.5, 3.8};
        Number[] expected = {1.0, 2.5, 3.8};
        
        Number[] result = DataUtilities.createNumberArray(input);
        
        assertArrayEquals("The created array should match the expected values", expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNumberArray_NullData() {
        DataUtilities.createNumberArray(null);
    }

    @Test
    public void testCreateNumberArray_EmptyData() {
        double[] input = {};
        Number[] expected = {};
        
        Number[] result = DataUtilities.createNumberArray(input);
        
        assertArrayEquals("The created array should be empty when input is empty", expected, result);
    }
    
    @Test
    public void testCreateNumberArray2D_ValidData() {
        double[][] input = {
            {1.0, 2.5, 3.8},
            {4.0, 5.5, 6.1}
        };
        Number[][] expected = {
            {1.0, 2.5, 3.8},
            {4.0, 5.5, 6.1}
        };
        
        Number[][] result = DataUtilities.createNumberArray2D(input);
        
        assertArrayEquals("The created 2D array should match the expected values", expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNumberArray2D_NullData() {
        DataUtilities.createNumberArray2D(null);
    }

    @Test
    public void testCreateNumberArray2D_EmptyData() {
        double[][] input = {};
        Number[][] expected = {};
        
        Number[][] result = DataUtilities.createNumberArray2D(input);
        
        assertArrayEquals("The created 2D array should be empty when input is empty", expected, result);
    }
    
    @Test
    public void testGetCumulativePercentages_ValidData() {
        DefaultKeyedValues input = new DefaultKeyedValues();
        input.addValue(0, (Number) (5.0));  // Casting to Number
        input.addValue(1, (Number) (9.0));
        input.addValue(2, (Number) (2.0));

        KeyedValues result = DataUtilities.getCumulativePercentages(input);
        
        // Expected cumulative percentages
        DefaultKeyedValues expected = new DefaultKeyedValues();
        expected.addValue(0, (Number) (0.3125)); // 5 / 16
        expected.addValue(1, (Number) (0.875));  // (5 + 9) / 16
        expected.addValue(2, (Number) (1.0));    // (5 + 9 + 2) / 16

        // Compare result with expected
        assertEquals("The cumulative percentages should match the expected values", expected, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCumulativePercentages_NullData() {
        DataUtilities.getCumulativePercentages(null);
    }

    @Test
    public void testGetCumulativePercentages_EmptyData() {
        DefaultKeyedValues input = new DefaultKeyedValues();
        
        KeyedValues result = DataUtilities.getCumulativePercentages(input);
        
        // Check if the result is empty using getItemCount()
        assertEquals("The result should be empty for empty input data", 0, result.getItemCount());
    }
    
 // <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
 // Lab 3 CODE START
 // <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>

    //Testing v == null edge case for getCumulativePercentage()
 	@Test
 	public void testGetCumulativePercentages_NullInValidKeyedValues() {
 	    Mockery context = new Mockery();
 	    final KeyedValues values = context.mock(KeyedValues.class);

 	    context.checking(new Expectations() {{
 	        atLeast(1).of(values).getItemCount(); will(returnValue(3));

 	        atLeast(1).of(values).getValue(0); will(returnValue(2.0));
 	        atLeast(1).of(values).getValue(1); will(returnValue(4.0));
 	        atLeast(1).of(values).getValue(2); will(returnValue(null));

 	        atLeast(1).of(values).getKey(0); will(returnValue(1));
 	        atLeast(1).of(values).getKey(1); will(returnValue(2));
 	        atLeast(1).of(values).getKey(2); will(returnValue(3));
 	    }});

 	   DefaultKeyedValues expected = new DefaultKeyedValues();
       expected.addValue(0, (Number) (0.3333333333333333)); // 2 / 6
       expected.addValue(1, (Number) (1.0));  // (2 + 4) / 6
       expected.addValue(2, (Number) (1.0));    // (2 + 4 + null) / 6
       
       KeyedValues result = DataUtilities.getCumulativePercentages(values);

       for (int i = 0; i < expected.getItemCount(); i++) {
           assertEquals("The " + i + "th value should be" + expected.getValue(i), expected.getValue(i), result.getValue(i));
       }
 	}
 	
 	//Testing Equality of Arrays
 	@Test
 	public void testEqual2DArrays_ANullBNull() {
 		double[][] a = null;
 		double[][] b = null;
 		assertEquals("This should return true", true, DataUtilities.equal(a, b));
 	}
 	
 	@Test
 	public void testEqual2DArrays_ANullBNotNull() {
 		double[][] a = null;
 		double[][] b = { {1.0, 2.0}, {3.0, 4.0} };
 		assertEquals("This should return false", false, DataUtilities.equal(a, b));
 	}
 	
 	@Test
 	public void testEqual2DArrays_DifferentLengths() {
 	    double[][] a = { {1.0, 2.0}, {3.0, 4.0} };
 	    double[][] b = { {1.0, 2.0} };
 	    assertEquals("This should return false", false, DataUtilities.equal(a, b));
 	}
 	
 	@Test
 	public void testEqual2DArrays_BNullANotNull() {
 	    double[][] a = { {1.0, 2.0}, {3.0, 4.0} };
 	    double[][] b = null;
 	    assertEquals("This should return false", false, DataUtilities.equal(a, b));
 	}
 	
 	@Test
 	public void testEqual2DArrays_SameLengthsDifferentValues() {
 	    double[][] a = { {1.0, 2.0}, {3.0, 4.0} };
 	    double[][] b = { {1.1, 2.0}, {3.0, 4.5} };
 	    assertEquals("This should return false", false, DataUtilities.equal(a, b));
 	}
 	
 	@Test
 	public void testEqual2DArrays_SameLengthsSameValues() {
 	    double[][] a = { {1.0, 2.0}, {3.0, 4.0} };
 	    double[][] b = { {1.0, 2.0}, {3.0, 4.0} };
 	    assertEquals("This should return true", true, DataUtilities.equal(a, b));
 	}
 	
 	//Testing clone method
    @Test
    public void testClone_NormalArray() {
        double[][] source = { {1.0, 2.0}, {3.0, 4.0} };
        double[][] result = DataUtilities.clone(source);
        
        for (int i = 0; i < source.length; i++) {
            assertArrayEquals("Row " + i + " should match", source[i], result[i], 0.0001);
        }
    }
    
    @Test
    public void testClone_ArrayWithNullSubArray() {
        double[][] source = { {1.0, 2.0}, null, {3.0, 4.0} };
        double[][] result = DataUtilities.clone(source);
        
        for (int i = 0; i < source.length; i++) {
            assertArrayEquals("Row " + i + " should match", source[i], result[i], 0.0001);
        }
    }

    //Testing calculating the sum of values in a column
    @Test
    public void testCalculateColumnTotal3Param_ValidData() {
        Mockery context = new Mockery();
        final Values2D values = context.mock(Values2D.class);
        
        context.checking(new Expectations() {{
            allowing(values).getRowCount(); will(returnValue(5)); // Total rows in dataset

            allowing(values).getValue(0, 1); will(returnValue(5.0));
            allowing(values).getValue(1, 1); will(returnValue(3.0));
            allowing(values).getValue(2, 1); will(returnValue(null)); // Null value should be ignored
            allowing(values).getValue(3, 1); will(returnValue(7.0));
            allowing(values).getValue(4, 1); will(returnValue(-2.0));
        }});

        int[] validRows = {0, 1, 2, 3, 4};  // Selecting all rows including one null

        double result = DataUtilities.calculateColumnTotal(values, 1, validRows);

        assertEquals("Total sum should be 13.0", 13.0, result, 0.0001);
    }
    

    @Test
    public void testCalculateColumnTotal3Params_InvalidRowIndex() {
        Mockery context = new Mockery();
        final Values2D values = context.mock(Values2D.class);

        context.checking(new Expectations() {{
            allowing(values).getRowCount(); will(returnValue(3)); // Only 3 rows exist
            allowing(values).getValue(0, 1); will(returnValue(4.0));
            allowing(values).getValue(1, 1); will(returnValue(6.0));
        }});

        int[] validRows = {0, 1, 3}; // Row index 3 is out of bounds

        double result = DataUtilities.calculateColumnTotal(values, 1, validRows);

        assertEquals("Total should be 10.0", 10.0, result, 0.0001);
    }
    
    //Testing calculating the sum of values in one row
    @Test
    public void testCalculateRowTotal3Param_ValidData() {
        Mockery context = new Mockery();
        final Values2D values = context.mock(Values2D.class);

        context.checking(new Expectations() {{
            allowing(values).getColumnCount(); will(returnValue(5));

            allowing(values).getValue(1, 0); will(returnValue(5.0));
            allowing(values).getValue(1, 1); will(returnValue(3.0));
            allowing(values).getValue(1, 2); will(returnValue(null)); // Null value should be ignored
            allowing(values).getValue(1, 3); will(returnValue(7.0));
            allowing(values).getValue(1, 4); will(returnValue(-2.0));
        }});

        int[] validCols = {0, 1, 2, 3, 4}; // Selecting all columns including one null

        double result = DataUtilities.calculateRowTotal(values, 1, validCols);

        assertEquals("Total sum should be 13.0", 13.0, result, 0.0001);
    }
    
    @Test
    public void testCalculateRowTotal3Param_InvalidColumnIndex() {
        Mockery context = new Mockery();
        final Values2D values = context.mock(Values2D.class);

        context.checking(new Expectations() {{
            allowing(values).getColumnCount(); will(returnValue(3)); // Only 3 columns exist
            allowing(values).getValue(1, 0); will(returnValue(4.0));
            allowing(values).getValue(1, 1); will(returnValue(6.0));
        }});

        int[] validCols = {0, 1, 3}; // Column index 3 is out of bounds

        double result = DataUtilities.calculateRowTotal(values, 1, validCols);

        assertEquals("Total should be 10.0", 10.0, result, 0.0001);
    }
    

 // <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
 // Lab 3 END OF CODE
 // <><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><>
}


