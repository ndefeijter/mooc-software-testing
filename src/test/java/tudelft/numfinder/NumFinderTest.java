package tudelft.numfinder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NumFinderTest {

    private NumFinder numFinder;

    @BeforeEach
    public void beforeEach() {
        numFinder = new NumFinder();
    }

    @Test
    public void find_null_throwIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    numFinder.find(null);
                }
        );
    }

    @Test
    public void find_emptyArray_throwIllegalArgumentException() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    numFinder.find(new int[]{});
                }
        );
    }

    @Test
    public void find_oneValue_MIN_minMaxEqualsMIN() {
        numFinder.find(new int[]{Integer.MIN_VALUE});
        assertEquals(Integer.MIN_VALUE, numFinder.getSmallest());
        assertEquals(Integer.MIN_VALUE, numFinder.getLargest());
    }

    @Test
    public void find_oneValue_MAX_minMaxEqualsMAX() {
        numFinder.find(new int[]{Integer.MAX_VALUE});
        assertEquals(Integer.MAX_VALUE, numFinder.getSmallest());
        assertEquals(Integer.MAX_VALUE, numFinder.getLargest());
    }

    @Test
    public void find_twoValues_MAX_MIN_minEqualsMIN_maxEqualsMAX() {
        numFinder.find(new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE});
        assertEquals(Integer.MIN_VALUE, numFinder.getSmallest());
        assertEquals(Integer.MAX_VALUE, numFinder.getLargest());
    }

    @Test
    public void find_threeValues_zero_minusOne_plusOne_minEqualsMinusOne_maxEqualsPlusOne() {
        numFinder.find(new int[]{0, -1, 1});
        assertEquals(-1, numFinder.getSmallest());
        assertEquals(1, numFinder.getLargest());
    }
}
