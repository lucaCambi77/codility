package it.cambi.codility.hackerRank;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HackerRank6NomadsTest {

  private static String api = "https://jsonmock.hackerrank.com/api/article_users";

  @Test
  public void getUsersName() throws IOException {
    assertEquals(
        Arrays.asList("coloneltcb", "dmmalam", "epaga", "mpweiher", "olalonde", "replicatorblog"),
        getUsersName(100));
  }

  public List<String> getUsersName(int threshold) throws IOException {
    URL url = new URL(api);
    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestProperty("accept", "application/json");
    InputStream responseStream = connection.getInputStream();

    String text =
        new BufferedReader(new InputStreamReader(responseStream, StandardCharsets.UTF_8))
            .lines()
            .collect(Collectors.joining(""));

    String regex = "\"total_pages\":([\\d+]*),";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(text);

    List<String> solution = new ArrayList<>();

    if (matcher.find()) {
      int totalPages = Integer.parseInt(matcher.group(1));

      while (totalPages > 0) {
        url = new URL(api + "?page=" + totalPages);
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");
        responseStream = connection.getInputStream();

        text =
            new BufferedReader(new InputStreamReader(responseStream, StandardCharsets.UTF_8))
                .lines()
                .collect(Collectors.joining(""));

        regex = "\"data\":\\[(.*?})\\]}";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(text);

        if (matcher.find()) {
          String users = matcher.group(1);

          regex = "\\{(.*?})\\,*";
          pattern = Pattern.compile(regex);
          matcher = pattern.matcher(users);

          while (matcher.find()) {

            String countRexEx = "\"submission_count\":([\\d+]*),";
            Pattern pattern1 = Pattern.compile(countRexEx);
            Matcher matcher1 = pattern1.matcher(matcher.group(1));

            if (matcher1.find() && Integer.parseInt(matcher1.group(1)) > threshold) {
              countRexEx = "\"username\":\"(.*?)\"\\,";
              pattern1 = Pattern.compile(countRexEx);
              matcher1 = pattern1.matcher(matcher.group(1));
              if (matcher1.find()) solution.add(matcher1.group(1));
            }
          }

          totalPages--;
        }
      }
    }

    return solution.stream().sorted().collect(Collectors.toList());
  }
}
