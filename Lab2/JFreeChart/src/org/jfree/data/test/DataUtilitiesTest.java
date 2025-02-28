package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;
import java.security.InvalidParameterException;


public class DataUtilitiesTest {
    
    @Test
    public void testCalculateColumnTotal_ValidData() {
        Mockery context = new Mockery();
        Values2D mockData = context.mock(Values2D.class);
        
        context.checking(new Expectations() {{
            allowing(mockData).getRowCount(); will(returnValue(3));
            allowing(mockData).getValue(0, 1); will(returnValue(5.0));
            allowing(mockData).getValue(1, 1); will(returnValue(3.0));
            allowing(mockData).getValue(2, 1); will(returnValue(2.0));
        }});
        
        double result = DataUtilities.calculateColumnTotal(mockData, 1);
        assertEquals("Sum of column 1 should be 10.0", 10.0, result, 0.0001);
    }
    
    @Test
    public void testCalculateColumnTotal_EmptyData() {
        Mockery context = new Mockery();
        Values2D mockData = context.mock(Values2D.class);
        
        context.checking(new Expectations() {{
            allowing(mockData).getRowCount(); will(returnValue(0));
        }});
        
        double result = DataUtilities.calculateColumnTotal(mockData, 1);
        assertEquals("Sum should be 0 for an empty table", 0.0, result, 0.0001);
    }
    
    @Test
    public void testCalculateColumnTotal_NullValues() {
        Mockery context = new Mockery();
        Values2D mockData = context.mock(Values2D.class);
        
        context.checking(new Expectations() {{
            allowing(mockData).getRowCount(); will(returnValue(3));
            allowing(mockData).getValue(0, 1); will(returnValue(null));
            allowing(mockData).getValue(1, 1); will(returnValue(1.0));
            allowing(mockData).getValue(2, 1); will(returnValue(5.0));
        }});
        
        double result = DataUtilities.calculateColumnTotal(mockData, 1);
        assertEquals("Sum should only include non-null values", 6.0, result, 0.0001);
    }
    @Test(expected = InvalidParameterException.class)
    public void testCalculateColumnTotal_NullData() {
        DataUtilities.calculateColumnTotal(null, 1);
    }
    @Test
    public void testCalculateRowTotal_ValidData() {
        Mockery context = new Mockery();
        Values2D mockData = context.mock(Values2D.class);

        context.checking(new Expectations() {{
            allowing(mockData).getColumnCount(); will(returnValue(3)); // 3 columns
            allowing(mockData).getValue(1, 0); will(returnValue(4.0)); // Value at row 1, column 0
            allowing(mockData).getValue(1, 1); will(returnValue(2.0)); // Value at row 1, column 1
            allowing(mockData).getValue(1, 2); will(returnValue(3.0)); // Value at row 1, column 2
        }});

        double result = DataUtilities.calculateRowTotal(mockData, 1);
        assertEquals("Sum of row 1 should be 9.0", 9.0, result, 0.0001); // 4.0 + 2.0 + 3.0 = 9.0
    }
    
    @Test
    public void testCalculateRowTotal_EmptyData() {
        Mockery context = new Mockery();
        Values2D mockData = context.mock(Values2D.class);
        
        context.checking(new Expectations() {{
            allowing(mockData).getColumnCount(); will(returnValue(0));
        }});
        
        double result = DataUtilities.calculateRowTotal(mockData, 1);
        assertEquals("Sum should be 0 for an empty table", 0.0, result, 0.0001);
    }
    
    @Test
    public void testCalculateRowTotal_NullValues() {
        Mockery context = new Mockery();
        Values2D mockData = context.mock(Values2D.class);
        
        context.checking(new Expectations() {{
            allowing(mockData).getColumnCount(); will(returnValue(3));
            allowing(mockData).getValue(1, 0); will(returnValue(null));
            allowing(mockData).getValue(1, 1); will(returnValue(2.0));
            allowing(mockData).getValue(1, 2); will(returnValue(null));
        }});
        
        double result = DataUtilities.calculateRowTotal(mockData, 1);
        assertEquals("Sum should only include non-null values", 2.0, result, 0.0001);
    }
    
    @Test(expected = InvalidParameterException.class)
    public void testCalculateRowTotal_NullData() {
        DataUtilities.calculateRowTotal(null, 1);
    }
    
    @Test
    public void testCreateNumberArray_ValidData() {
        double[] input = {1.0, 2.5, 3.8};
        Number[] expected = {1.0, 2.5, 3.8};
        
        Number[] result = DataUtilities.createNumberArray(input);
        
        assertArrayEquals("The created array should match the expected values", expected, result);
    }

    @Test(expected = InvalidParameterException.class)
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

    @Test(expected = InvalidParameterException.class)
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

    @Test(expected = InvalidParameterException.class)
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
}
