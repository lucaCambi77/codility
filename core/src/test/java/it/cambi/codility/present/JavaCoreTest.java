/**
 *
 */
package it.cambi.codility.present;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author luca
 *
 */
//@ExtendWith(SystemOutRule.class)
@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class JavaCoreTest {

	private PrintStream out;

	@Spy
	Square quadrato = new Square();

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

		quadrato.disegna(0, 0);
		verify(out).print("Disegna un quadrato");

		verify(quadrato, times(0)).print();
		verify(quadrato, times(1)).print(0, 0);

	}

	public abstract class Shape {
		public void disegna(int anX, int anY) {
			System.out.print("Super classe forma");
		}
	}

	public class Square extends Shape {
		int unX = 1;
		int unY = 1;

		public Square() {
		}

		public void disegna(int unX, int unY) {
			print(unX, unY);
		}

		public void print(int unX, int unY) {
			System.out.print("Disegna un quadrato");

		}

		public void print() {
			System.out.print("Disegna un quadrato di lato 1");

		}
	}

	public class Circle extends Shape {
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

	}

	public class A {
		public void disegna() {
			System.out.print("Super classe A");
		}
	}

	public class B extends Shape {
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

		int a = -1;
		String b = "prova";
		try {
			a = Integer.parseInt(b);
		} catch (NumberFormatException e) {
			System.out.print("Exception");
		}

		verify(out).print("Exception");
		assertEquals(-1, a);
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

	}

	@Test
	@Order(7)
	public void testInheritance1() {

		B1 b = new C1();
		// b.method1(); this is correct
		b.method2();

		verify(out).print("C1 method2");

	}

	@Test
	@Order(8)
	public void threads() throws InterruptedException {
		// start() not working, we need to mock it
		Thread1 t1 = Mockito.spy(new Thread1());
		Thread2 t2 = Mockito.spy(new Thread2());
		t1.start();
		t2.start();

		t1.join();
		t2.join();

		Mockito.verify(t1).run();
		Mockito.verify(t2).run();

		InOrder orderVerifier = Mockito.inOrder(out);
		orderVerifier.verify(out, times(1)).print("Thread2");
		orderVerifier.verify(out, times(1)).print("Thread1");

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

class Thread1 extends Thread {
	@Override
	public void run() {
		try {
			Thread.sleep(2000);
			System.out.print("Thread1");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

class Thread2 extends Thread {
	@Override
	public void run() {
		try {
			Thread.sleep(500);
			System.out.print("Thread2");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
