/** */
package it.cambi.codility.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** @author luca */
@Getter
@Setter
@NoArgsConstructor
public class DataKey {

  protected String name;
  protected int id;

  @Override
  public String toString() {
    return "DataKey [name=" + name + ", id=" + id + "]";
  }
}
