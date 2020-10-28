package it.cambi.codility.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class AbstractTestUtilTest {

  @InjectMocks private TestUtil abstractTestUtil;

  @BeforeAll
  static void setUp() {
    System.setProperty("os.name", "windows");
  }

  @Test
  void should_match_carriage_return_by_system_property() {
    ReflectionTestUtils.setField(abstractTestUtil, "os", "mac");
    assertEquals("\n", abstractTestUtil.getCarriageReturn());
  }

  @Test
  void should_match_carriage_by_os() {
    ReflectionTestUtils.setField(abstractTestUtil, "os", "windows");
    assertEquals("\r\n", abstractTestUtil.getCarriageReturn());
  }
}
