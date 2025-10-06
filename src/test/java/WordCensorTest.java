import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("WordCensor – String replacement tests")
public class WordCensorTest {

    private String runWithInput(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        java.io.InputStream originalIn = System.in;
        try {
            System.setIn(in);
            System.setOut(new PrintStream(out));
            WordCensor.main(new String[]{});
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
        return out.toString();
    }

    @Test
    @DisplayName("Replaces a single banned word with exactly ***")
    void testSingleBannedWord() {
        String output = runWithInput("this is dang hard\n");

        assertTrue(output.contains("***"),
            "❌ Expected censored output to contain '***' but got: " + output);

        assertFalse(output.contains("****"),
            "❌ Expected exactly three asterisks (***), but found four (****) in output: " + output);

        assertFalse(output.contains("dang"),
            "❌ Expected 'dang' to be censored, but found it in output: " + output);

        assertTrue(output.contains("Censored:"),
            "❌ Expected output to include the label 'Censored:', but got: " + output);
    }

    @Test
    @DisplayName("Replaces multiple banned words, each with exactly ***")
    void testMultipleBannedWords() {
        String output = runWithInput("oops that was nuts and dang yikes\n");
        int exactThree = output.split("(?<!\\*)\\*\\*\\*(?!\\*)").length - 1;

        assertTrue(exactThree >= 4,
            "❌ Expected at least 4 banned words censored as '***', but found " + exactThree + " in output: " + output);

        assertFalse(output.contains("****"),
            "❌ Expected each banned word to be replaced with '***' only, but found '****' in output: " + output);

        assertFalse(output.contains("oops"),
            "❌ Expected 'oops' to be censored, but found in output: " + output);

        assertFalse(output.contains("nuts"),
            "❌ Expected 'nuts' to be censored, but found in output: " + output);

        assertFalse(output.contains("dang"),
            "❌ Expected 'dang' to be censored, but found in output: " + output);

        assertFalse(output.contains("yikes"),
            "❌ Expected 'yikes' to be censored, but found in output: " + output);
    }

    @Test
    @DisplayName("Leaves sentences with no banned words unchanged (besides label)")
    void testNoBannedWords() {
        String output = runWithInput("hello world\n");

        assertTrue(output.contains("Censored:"),
            "❌ Expected output to include the label 'Censored:', but got: " + output);

        assertTrue(output.toLowerCase().contains("hello"),
            "❌ Expected 'hello' to remain unchanged, but output was: " + output);

        assertTrue(output.toLowerCase().contains("world"),
            "❌ Expected 'world' to remain unchanged, but output was: " + output);
    }
}
