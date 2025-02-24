package hire.tagetik.universita;

public class Studente extends Person {

  private String matricola;
  private String universita;

  public Studente(String nome, String cognome) {
    super(nome, cognome);
  }

  public Studente(String nome, String cognome, String matricola, String universita) {
    super(nome, cognome);
    this.matricola = matricola;
    this.universita = universita;
  }

  public String getMatricola() {
    return matricola;
  }

  public void setMatricola(String matricola) {
    this.matricola = matricola;
  }

  public String getUniversita() {
    return universita;
  }

  public void setUniversita(String universita) {
    this.universita = universita;
  }

  @Override
  public String toString() {
    return String.format(
        "Studente : nome -> %s, cognome -> %s, matricola -> %s, università -> %s",
        getNome(), getCognome(), getMatricola(), getUniversita());
  }
}
