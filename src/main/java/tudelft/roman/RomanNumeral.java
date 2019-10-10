package tudelft.roman;

import java.util.HashMap;
import java.util.Map;

public class RomanNumeral {

    private static Map<Character, Integer> map;

    static {
        map = new HashMap<Character, Integer>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
    }

    public int convert(final String s) {
        if (null == s) {
            throw new IllegalArgumentException("Required parameter 's' is null");
        }
        if (0 == s.length()) {
            throw new IllegalArgumentException("Required parameter 's' is empty");
        }

        int convertedNumber = 0;
        for(int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);

            if ( ! map.containsKey(currentChar )) {
                throw new IllegalArgumentException("Parameters contains illegal character: " + currentChar);
            }

            int currentNumber = map.get(currentChar);
            int next = i+1 < s.length() ? map.get(s.charAt(i+1)) : 0;

            if(currentNumber >= next)
                convertedNumber += currentNumber;
            else
                convertedNumber -= currentNumber;
        }

        return convertedNumber;

    }
}
