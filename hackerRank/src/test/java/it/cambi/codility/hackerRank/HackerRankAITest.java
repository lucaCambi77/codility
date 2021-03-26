/** */
package it.cambi.codility.hackerRank;

import it.cambi.codility.util.TestUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/** @author luca */
class HackerRankAITest extends TestUtil {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private final PrintStream originalErr = System.err;

  @BeforeEach
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @AfterEach
  public void restoreStreams() {
    System.setOut(originalOut);
    System.setErr(originalErr);
  }

  @Test
  public void botSavesPrincess() {
    botSavesPrincess(new String[] {"3", "---", "-m-", "--p"});

    assertEquals(
        "DOWN" + getCarriageReturn() + "RIGHT" + getCarriageReturn(), outContent.toString());
    outContent.reset();

    botSavesPrincess(new String[] {"3", "p--", "-m-", "---"});
    assertEquals("UP" + getCarriageReturn() + "LEFT" + getCarriageReturn(), outContent.toString());
  }

  private void botSavesPrincess(String[] args) {
    int boatX = 0, boatY = 0, queenX = 0, queenY = 0;

    for (int i = 1; i < args.length; i++) {
      String row = args[i];

      for (int j = 0; j < row.length(); j++) {
        if (row.charAt(j) == 'm') {
          boatX = i;
          boatY = j + 1;
        } else if (row.charAt(j) == 'p') {
          queenX = i;
          queenY = j + 1;
        }
      }
    }

    int rowDiff = boatX - queenX;
    int colDiff = boatY - queenY;

    if (rowDiff > 0) {
      for (int i = 0; i < rowDiff; i++) {
        System.out.println("UP");
      }
    } else if (rowDiff < 0) {
      for (int i = rowDiff; i < 0; i++) {
        System.out.println("DOWN");
      }
    }

    if (colDiff > 0) {
      for (int i = 0; i < colDiff; i++) {
        System.out.println("LEFT");
      }
    } else if (colDiff < 0) {
      for (int i = colDiff; i < 0; i++) {
        System.out.println("RIGHT");
      }
    }
  }
}
