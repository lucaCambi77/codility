/**
 * 
 */
package it.cambi.codility.coreJava;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * @author luca
 *
 */
@TestMethodOrder(OrderAnnotation.class)
public class EqualsTest {

	/**
	 * Object are not equals as no {@link java.lang.Object#equals(Object)}} is not
	 * implemented so two different integers are returned for 2 objects
	 */
	@Test
	@Order(1)
	public void notEquals() {
		Map<DataKey, Integer> hm = getAllData();

		DataKey dk = new DataKey();
		dk.setId(1);
		dk.setName("Pankaj");
		System.out.println(dk.hashCode());

		Integer value = hm.get(dk);

		assertNull(value);
	}

	private Map<DataKey, Integer> getAllData() {
		Map<DataKey, Integer> hm = new HashMap<>();

		DataKey dk = new DataKey();
		dk.setId(1);
		dk.setName("Pankaj");
		System.out.println(dk.hashCode());

		hm.put(dk, 10);

		return hm;
	}

	/**
	 * Object are not equals even if {@link java.lang.Object#equals(Object)}} is
	 * implemented and {@link java.lang.Object#hashCode()}} should be the same
	 * integers for objects. Anonymous class are used so equals will fail objects
	 * have different classes
	 */
	@Test
	@Order(2)
	public void notEquals1() {
		Map<DataKey, Integer> hm = getAllData1();

		DataKey dk = new DataKey() {

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
				DataKey other = (DataKey) obj;
				if (id != other.id)
					return false;
				if (name == null) {
					if (other.name != null)
						return false;
				} else if (!name.equals(other.name))
					return false;
				return true;
			}
		};

		dk.setId(1);
		dk.setName("Pankaj");
		System.out.println(dk.hashCode());

		Integer value = hm.get(dk);

		assertNull(value);

	}

	private Map<DataKey, Integer> getAllData1() {
		Map<DataKey, Integer> hm = new HashMap<>();

		DataKey dk = new DataKey() {
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
				DataKey other = (DataKey) obj;
				if (id != other.id)
					return false;
				if (name == null) {
					if (other.name != null)
						return false;
				} else if (!name.equals(other.name))
					return false;
				return true;
			}
		};

		dk.setId(1);
		dk.setName("Pankaj");
		System.out.println(dk.hashCode());

		hm.put(dk, 10);

		return hm;
	}

	/**
	 * Object are equals as {@link java.lang.Object#equals(Object)}} is implemented
	 * properly. {@link java.lang.Object#hashCode()}} should be the same integers
	 * for 2 objects
	 */
	@Test
	@Order(3)
	public void equals() {
		Map<DataKeyEquals, Integer> hm = getAllData2();

		DataKeyEquals dk = new DataKeyEquals();
		dk.setId(1);
		dk.setName("Pankaj");
		System.out.println(dk.hashCode());

		Integer value = hm.get(dk);

		assertNotNull(value);
	}

	private Map<DataKeyEquals, Integer> getAllData2() {
		Map<DataKeyEquals, Integer> hm = new HashMap<>();

		DataKeyEquals dk = new DataKeyEquals();
		dk.setId(1);
		dk.setName("Pankaj");
		System.out.println(dk.hashCode());

		hm.put(dk, 10);

		return hm;
	}
}
