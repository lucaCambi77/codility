package it.cambi.codility.pattern;

import it.cambi.codility.pattern.singleton.Singleton;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SingletonTest {

    @Test
    void test() {
        Singleton s = Singleton.load();
        assertNotNull(s);
        assertEquals(s, Singleton.load());
    }
}
