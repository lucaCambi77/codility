/**
 * 
 */
package it.cambi.codility.coreJava;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author luca
 *
 */

@Getter
@Setter
@NoArgsConstructor
public class DataKeyEquals {

	protected String name;
	protected int id;

	// getter and setter methods

	@Override
	public String toString() {
		return "DataKey [name=" + name + ", id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataKeyEquals other = (DataKeyEquals) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
