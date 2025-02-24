package hire.tagetik.universita;

import java.util.List;

public class Universita {

  private static Universita singleton;

  private Universita(List<Docente> docenti, List<Studente> studenti) {
    // private constructor
    this.docenti = docenti;
    this.studenti = studenti;
  }

  private final List<Docente> docenti;
  private final List<Studente> studenti;

  public static synchronized Universita loadUniversita(List<Docente> docenti, List<Studente> studenti) {
    if (singleton == null) {
      singleton = new Universita(docenti, studenti);
    }
    return singleton;
  }

  private List<Docente> getDocenti() {
    return docenti;
  }

  private List<Studente> getStudenti() {
    return studenti;
  }

  public List<Docente> getDocentiUniversita() {
    return singleton.getDocenti();
  }

  public List<Studente> getStudentiUniversita() {
    return singleton.getStudenti();
  }
}
