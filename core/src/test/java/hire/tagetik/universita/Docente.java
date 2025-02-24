package hire.tagetik.universita;

public class Docente extends Person {

  private String materia;
  private int salario;

  public String getMateria() {
    return materia;
  }

  public void setMateria(String materia) {
    this.materia = materia;
  }

  public int getSalario() {
    return salario;
  }

  public void setSalario(int salario) {
    this.salario = salario;
  }

  public Docente(String nome, String cognome, String materia, int salario) {
    super(nome, cognome);
    this.materia = materia;
    this.salario = salario;
  }

  @Override
  public String toString() {
    return String.format(
        "Docente : nome -> %s, cognome -> %s, materia -> %s, salario -> %s",
        getNome(), getCognome(), getMateria(), getSalario());
  }
}
