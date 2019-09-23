/**
 * 
 */
package it.cambi.codility.present;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.awt.Button;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 * @author luca
 *
 */
@ExtendWith(SystemOutRule.class)
@TestMethodOrder(OrderAnnotation.class)
public class JavaCoreTest {

	private PrintStream out;

	@BeforeEach
	public void setUpStreams() {

		out = mock(PrintStream.class);
		System.setOut(out);
	}

	@Test
	@Order(1)
	public void testButton() {
		Button p, q;
		p = new Button("OK");
		q = p;
		q.setLabel("Cancel");

		assertEquals("Cancel", q.getLabel());
		assertEquals("Cancel", p.getLabel());

	}

	@Test
	@Order(2)
	public void testForma() {

		Quadrato q = new Quadrato();
		q.disegna(0, 0);
		verify(out).print("Disegna un quadrato");

		// assertEquals("Disegna un quadrato", outContent.toString());
	}

	public abstract class Forma {
		public void disegna(int anX, int anY) {
			System.out.print("Super classe forma");
		}
	}

	public class Quadrato extends Forma {
		public void disegna(int unX, int unY) {
			System.out.print("Disegna un quadrato");

		}
	}

	public class Cerchio extends Forma {
		public void disegna(int unX, int unY) {
			System.out.print("Disegna un cerchio");
		}
	}

	@Test
	@Order(3)
	public void testStringEquals() {
		String a = new String("Test");
		String b = new String("test");
		if (a == b)
			System.out.print("Uguale");
		else
			System.out.print("Diverso");

		verify(out).print("Diverso");

		// assertEquals("Diverso", outContent.toString());

	}

	public class A {
		public void disegna() {
			System.out.print("Super classe A");
		}
	}

	public class B extends Forma {
		public void disegna() {
			System.out.print("Classe B");

		}
	}

	public class C extends B {
		public void disegna() {
			System.out.print("Classe C");
		}
	}

	@Test
	@Order(4)
	public void testAssignObject() {
		A a = new A();
		B b = new B();
		C c = new C();

		Object xa = a;
		Object xb = b;
		Object xc = c;

	}

	@Test
	@Order(5)
	public void testParseString() {

		int a;
		String b = "prova";
		try {
			a = Integer.parseInt(b);
		} catch (Exception e) {
			System.out.println("Exception");
		}
		// Unreachable code
		// catch (NumberFormatException nfe) {
		// System.out.println("NumberFormatException");
		// }
	}

	/**
	 * @author luca
	 *
	 */
	public interface B1 {
		public void method1();

		public void method2();
	}

	public abstract class A1 implements B1 {
		public void method1() {
			System.out.print("A1 method1");
		}

		public abstract void method2();
	}

	public class C1 extends A1 {

		public void method1() {
			super.method1();
		}

		@Override
		public void method2() {
			System.out.print("C1 method2");
		}
	}

	@Test
	@Order(6)
	public void testInheritance() {
		A1 a = new C1();
		// a.method1(); this is correct
		a.method2();
		verify(out).print("C1 method2");

		// assertEquals("C1 method2", outContent.toString());

	}

	@Test
	@Order(7)
	public void testInheritance1() {

		B1 b = new C1();
		// b.method1(); this is correct
		b.method2();
		// assertEquals("C1 method2", outContent.toString());
		verify(out).print("C1 method2");

	}

	public class Thread1 extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(2000);
				System.out.println("Thread1");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	public class Thread2 extends Thread {
		@Override
		public void run() {
			try {
				Thread.sleep(500);
				System.out.println("Thread2");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	@Test
	@Order(8)
	public void threads() {

		Thread1 t1 = new Thread1();
		Thread2 t2 = new Thread2();
		t1.run();
		t2.run();
		// Print Thread1 and after Thread2

	}

	@Test
	@Order(9)
	public void modifyStringInMethod() {
		String s = "Test";
		modifyStringInMethod(s);
		assertEquals("Test", s);

	}

	public void modifyStringInMethod(String s) {

		assertEquals("Test 1", s.concat(" 1"));

	}
}
