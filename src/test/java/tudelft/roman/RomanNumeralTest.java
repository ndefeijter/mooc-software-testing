package tudelft.roman;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

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
        Assertions.assertEquals(   1, roman.convert("I"));
        Assertions.assertEquals(   5, roman.convert("V"));
        Assertions.assertEquals(  10, roman.convert("X"));
        Assertions.assertEquals(  50, roman.convert("L"));
        Assertions.assertEquals( 100, roman.convert("C"));
        Assertions.assertEquals( 500, roman.convert("D"));
        Assertions.assertEquals(1000, roman.convert("M"));
    }

    @Test
    public void numberWithManyDigits() {
        RomanNumeral roman = new RomanNumeral();
        Assertions.assertEquals(3341, roman.convert("MMDDCCLLXXVVVIIIIII"));
    }

    @Test
    public void numberWithSubtractiveNotation() {
        RomanNumeral roman = new RomanNumeral();
        Assertions.assertEquals(4, roman.convert("IV"));
        Assertions.assertEquals(5, roman.convert("VX"));
        Assertions.assertEquals(40, roman.convert("XL"));
        Assertions.assertEquals(50, roman.convert("LC"));
        Assertions.assertEquals(400, roman.convert("CD"));
        Assertions.assertEquals(500, roman.convert("DM"));
        Assertions.assertEquals(334, roman.convert("IVXLCDM"));
    }

    @Test
    public void numberWithAndWithoutSubtractiveNotation() {
        RomanNumeral roman = new RomanNumeral();
        int result = roman.convert("XLIV");
        Assertions.assertEquals(44, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"VX", "XXC"})
    public void numberWithIllegalSubstractions(final String illegalSubstraction) {
        RomanNumeral romanNumeral = new RomanNumeral();
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    romanNumeral.convert(illegalSubstraction);
                }
        );
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
