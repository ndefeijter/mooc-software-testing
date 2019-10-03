package tudelft.numfinder;

public class NumFinder {
    private int smallest = Integer.MAX_VALUE;
    private int largest = Integer.MIN_VALUE;

    public void find(int[] nums) {
        if (null == nums) {
            throw new IllegalArgumentException("required parameter 'nums' may not be null");
        }
        if (0 == nums.length) {
            throw new IllegalArgumentException("required parameter 'nums' may not be empty");
        }
        for(int n : nums) {

            if(n < smallest)
                smallest = n;
            else if (n > largest)
                largest = n;

        }
    }

    public int getSmallest () {
        return smallest;
    }

    public int getLargest () {
        return largest;
    }
}
