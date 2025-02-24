package hire.tagetik.universita;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TagetikTest {

  @Test
  void testStudente() {
    Studente person = new Studente("luca", "cambi", "matricola", "universita");
    assertEquals("luca", person.getNome());
    assertEquals("cambi", person.getCognome());
    assertEquals("matricola", person.getMatricola());
    assertEquals("universita", person.getUniversita());
  }

  @Test
  void testDocente() {
    Docente person = new Docente("luca", "cambi", "materia", 1000);
    assertEquals("luca", person.getNome());
    assertEquals("cambi", person.getCognome());
    assertEquals("materia", person.getMateria());
    assertEquals(1000, person.getSalario());
  }

  @Test
  void printPerson() {
    List<Person> personList =
        List.of(
            new Studente("luca", "cambi", "matricola", "universita"),
            new Docente("luca", "cambi", "materia", 1000));

    System.out.println(personList);
  }

  @Test
  void highestDocenteSalary() {

    List<Docente> personList =
        List.of(
            new Docente("luca", "primo", "materia1", 1000),
            new Docente("luca", "secondo", "materia2", 2000),
            new Docente("luca", "terzo", "materia3", 3000));

    int maxSalary = 0;

    Docente d = personList.stream().max(Comparator.comparingInt(Docente::getSalario)).get();

    assertEquals(3000, d.getSalario());
    assertEquals("terzo", d.getCognome());

    Docente docentMaxSalary = personList.get(0);

    for (Docente docente : personList) {
      if (docente.getSalario() > maxSalary) {
        maxSalary = docente.getSalario();
        docentMaxSalary = docente;
      }
    }

    assertEquals(3000, maxSalary);
    assertEquals("terzo", docentMaxSalary.getCognome());
  }

  @Test
  void testUniversita() {

    List<Docente> docenti =
        List.of(
            new Docente("luca", "primo", "materia1", 1000),
            new Docente("luca", "secondo", "materia2", 2000),
            new Docente("luca", "terzo", "materia3", 3000));

    List<Studente> studenti = List.of(new Studente("luca", "cambi", "matricola", "universita"));

    Universita universita = Universita.loadUniversita(docenti, studenti);

    assertEquals(3, universita.getDocentiUniversita().size());
    assertEquals(1, universita.getStudentiUniversita().size());
  }
}
