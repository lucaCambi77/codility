package hire.tagetik;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import org.junit.jupiter.api.Test;

public class BracketValidatorTest {

  @Test
  void validateBrackets() {
    assertTrue(validateBrackets("{[()]}"));
    assertTrue(validateBrackets("{[]}"));
    assertTrue(validateBrackets("{}"));
    assertTrue(validateBrackets("[]"));
  }

  @Test
  void invalidateBrackets() {
    assertFalse(validateBrackets("{[)]}"));
    assertFalse(validateBrackets("{[()]"));
    assertFalse(validateBrackets("{]}"));
    assertFalse(validateBrackets("]}"));
  }

  boolean validateBrackets(String brackets) {

    Map<Character, Character> map = new HashMap<>();
    map.put('(', ')');
    map.put('[', ']');
    map.put('{', '}');

    Stack<Character> stack = new Stack<>();

    for (char c : brackets.toCharArray()) {
      if (stack.isEmpty() || map.containsKey(c)) {
        stack.push(c);
        continue;
      }

      Character close = map.get(stack.peek());

      if (close != null && close.equals(c)) {
        stack.pop();
      } else {
        return false;
      }
    }

    return stack.isEmpty();
  }
}
