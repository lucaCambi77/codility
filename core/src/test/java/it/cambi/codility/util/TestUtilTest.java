package it.cambi.codility.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TestUtilTest {

  @InjectMocks private TestUtil testUtil;

  @BeforeAll
  static void setUp() {
    System.setProperty("os.name", "windows");
  }

  @Test
  void should_match_carriage_return_by_system_property() {
    assertEquals("\r\n", testUtil.getCarriageReturn());
  }

  @Test
  void should_match_carriage_by_os_not_windows() {
    ReflectionTestUtils.setField(testUtil, "os", "mac");
    assertEquals("\n", testUtil.getCarriageReturn());
  }

  @Test
  void should_match_carriage_by_os_windows() {
    ReflectionTestUtils.setField(testUtil, "os", "windows");
    assertEquals("\r\n", testUtil.getCarriageReturn());
  }
}
