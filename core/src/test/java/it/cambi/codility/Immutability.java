package it.cambi.codility;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Immutability {

  static class Person {
    private int id;
    private String name;
    private String surname;

    public Person(int id, String name, String surname) {
      this.id = id;
      this.name = name;
      this.surname = surname;
    }
  }

  static class PersonEqualsAndHashCode {
    private int id;
    private String name;
    private String surname;

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      PersonEqualsAndHashCode that = (PersonEqualsAndHashCode) o;
      return id == that.id
          && Objects.equals(name, that.name)
          && Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
      return Objects.hash(id, name, surname);
    }

    public PersonEqualsAndHashCode(int id, String name, String surname) {
      this.id = id;
      this.name = name;
      this.surname = surname;
    }
  }

  /**
   * For a class to be immutable, needs to be final, have final attributes and return a new object
   * for mutable fields
   */
  final class PersonImmutable {
    private final int id;
    private final String name;
    private final String surname;
    private final Date date;

    public Date getDate() {
      return new Date(date.getTime());
    }

    public PersonImmutable(int id, String name, String surname, Date date) {
      this.id = id;
      this.name = name;
      this.surname = surname;
      this.date = date;
    }
  }

  @Test
  public void testDuplicateInSet() {
    Set<Person> personSet = new HashSet<>();

    personSet.add(new Person(1, "name", "surname"));
    personSet.add(new Person(1, "name", "surname"));

    assertEquals(2, personSet.size());
  }

  @Test
  public void testNoDuplicateInSet() {
    Set<PersonEqualsAndHashCode> personSet = new HashSet<>();

    personSet.add(new PersonEqualsAndHashCode(1, "name", "surname"));
    personSet.add(new PersonEqualsAndHashCode(1, "name", "surname"));

    assertEquals(1, personSet.size());
  }

  @Test
  public void testImmutable() {
    Date date = new Date();
    PersonImmutable personImmutable = new PersonImmutable(1, "name", "surname", date);
    personImmutable.getDate().setTime(new Date().getTime());
    assertEquals(date.getTime(), personImmutable.getDate().getTime());
  }

  @Test
  public void testImmutableString() {
    String firstString = "John";
    String secondString = "John";

    assertTrue(firstString == secondString);
    assertFalse(firstString == new String("John"));

    doSomethingWithAString(firstString);
    assertTrue(firstString == "John");
  }

  private void doSomethingWithAString(String s) {
    // do stuff
    s = "new String";
  }
}
