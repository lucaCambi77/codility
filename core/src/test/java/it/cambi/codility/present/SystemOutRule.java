/** */
package it.cambi.codility.present;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;

/** @author luca */
public class SystemOutRule implements AfterEachCallback, BeforeEachCallback {
  private PrintStream out;

  @Override
  public void beforeEach(ExtensionContext context) throws Exception {
    out = mock(PrintStream.class);
    System.setOut(out);
  }

  @Override
  public void afterEach(ExtensionContext context) throws Exception {
    // TODO Auto-generated method stub

  }

  public String print() {
    return out.toString();
  }
}
