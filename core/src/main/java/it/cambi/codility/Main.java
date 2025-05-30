package it.cambi.codility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Main {
  public static void main(String[] args) {
    String dateString = "09.04.2025";
    Locale locale = new Locale("it", "CH");
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy", locale);

    try {
      Date date = sdf.parse(dateString);
      System.out.println("Parsed date: " + date);
    } catch (ParseException e) {
      System.out.println("Errore durante la conversione della data: " + e.getMessage());
    }
  }
}
