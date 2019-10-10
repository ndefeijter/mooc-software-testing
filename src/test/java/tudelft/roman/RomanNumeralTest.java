package tudelft.roman;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RomanNumeralTest {


    @Test
    public void singleNumber() {
        RomanNumeral roman = new RomanNumeral();
        int result = roman.convert("I");
        Assertions.assertEquals(1, result);
    }

    @Test
    public void numberWithManyDigits() {
        RomanNumeral roman = new RomanNumeral();
        int result = roman.convert("VIII");
        Assertions.assertEquals(8, result);
    }

    @Test
    public void numberWithSubtractiveNotation() {
        RomanNumeral roman = new RomanNumeral();
        int result = roman.convert("IV");
        Assertions.assertEquals(4, result);
    }

    @Test
    public void numberWithAndWithoutSubtractiveNotation() {
        RomanNumeral roman = new RomanNumeral();
        int result = roman.convert("XLIV");
        Assertions.assertEquals(44, result);
    }

    @Test
    public void convert_emptyString_throwsIllegalArgumentException() {
        final RomanNumeral romanNumeral = new RomanNumeral();
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    romanNumeral.convert("");
                }
        );
    }

    @ParameterizedTest
    @MethodSource("createWordsWithIllegalCharacters")
    public void convert_illegalCharacters_throwsIllegalArgumentException(final String illegalCharacters) {
        final RomanNumeral romanNumeral = new RomanNumeral();
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    romanNumeral.convert(illegalCharacters);
                }
        );
    }

    private static Stream<String> createWordsWithIllegalCharacters() {

        final List<String> validStrings = Arrays.asList("I", "V", "X", "L", "C", "D", "M");

        return IntStream.range('A', 'z')
                .peek(c -> System.out.println("Char: " + c))
                .mapToObj(Character::toString)
                .peek(s -> System.out.println("String: " + s))
                .filter(s -> ! validStrings.contains(s))
                .peek(s -> System.out.println("Filtered: " + s));
    }
}
