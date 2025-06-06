/**
 *
 */
package company.superawesome;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import it.cambi.superawesome.domain.Anagrams;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author luca
 *
 */
@ExtendWith(MockitoExtension.class)
public class AnagramsTest {

    private PrintStream out;

    private @Spy Anagrams anagrams = new Anagrams();

    private static final String fileDir = "src/test/resources/task/data";

    @BeforeEach
    public void setUpStreams() {
        out = mock(PrintStream.class);
        System.setOut(out);
    }

    @ParameterizedTest
    @CsvSource({ fileDir + "/example1.txt" })
    public void shouldPassReadFromFile(String file) throws IOException {

        try (BufferedReader buf = new BufferedReader(new FileReader(file))) {
            String line = buf.readLine();

            while (line != null) {
                System.out.println(line);
                line = buf.readLine();

            }
        }

        verify(out).println("abc");
        verify(out, times(2)).println("fun");
        verify(out).println("bac");
        verify(out).println("unf");
        verify(out).println("hello");
        verify(out).println("cba");

    }

    @ParameterizedTest
    @ValueSource(strings = { fileDir + "/example1.txt" })
    public void shouldPassExample1(String file) throws IOException {

        anagrams.printFromInput(file);

        verify(out).println("fun,unf");
        verify(out).println("abc,bac,cba");
        verify(out).println("hello");

        verify(out, times(3)).println(anyString());

    }

    @ParameterizedTest
    @ValueSource(strings = { fileDir + "/example2.txt" })
    public void shouldPassExample2(String file) throws IOException {

        anagrams.printFromInput(file);

        verify(out, times(156476)).println(anyString());
    }

    @ParameterizedTest
    @ValueSource(strings = { fileDir + "/longestAnagram.txt" })
    public void shouldPassLongestAnagram(String file) throws IOException {

        anagrams.printFromInput(file);

        verify(out, times(1)).println("hydroxydeoxycorticosterones,hydroxydesoxycorticosterone");

    }

    @ParameterizedTest
    @ValueSource(strings = { fileDir + "/caseSensitive.txt" })
    public void shouldPassCaseSensitiveWithEmptyLines(String file) throws IOException {

        anagrams.printFromInput(file);

        verify(out, times(1)).println("Hello,hello");

    }
}
