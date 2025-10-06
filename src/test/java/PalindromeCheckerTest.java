import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PalindromeCheckerTest {

    private String runWithInput(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        java.io.InputStream originalIn = System.in;
        try {
            System.setIn(in);
            System.setOut(new PrintStream(out));
            PalindromeChecker.main(new String[]{});
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
        return out.toString();
    }

    @Test
    @DisplayName("Identifies classic palindromes (case-insensitive)")
    void testPalindromePositive() {
        String input = "racecar\n";
        String output = runWithInput(input).toLowerCase();
        String expected = "racecar is a palindrome";

        assertTrue(output.contains(expected),
            "❌ Expected output to contain: \"" + expected + "\" but got: \"" + output + "\"");
    }

    @Test
    @DisplayName("Identifies non-palindromes")
    void testPalindromeNegative() {
        String input = "hello\n";
        String output = runWithInput(input).toLowerCase();
        String expected = "hello is not a palindrome";

        assertTrue(output.contains(expected),
            "❌ Expected output to contain: \"" + expected + "\" but got: \"" + output + "\"");
    }

    @Test
    @DisplayName("Counts single-character inputs as palindromes")
    void testSingleCharacter() {
        String input = "A\n";
        String output = runWithInput(input).toLowerCase();
        String expected = "a is a palindrome";

        assertTrue(output.contains(expected),
            "❌ Expected output to contain: \"" + expected + "\" but got: \"" + output + "\"");
    }
}
