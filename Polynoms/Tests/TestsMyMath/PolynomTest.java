package TestsMyMath;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exceptions.wrongDataException;
import myMath.Monom;
import myMath.Monom_Comperator;
import myMath.Polynom;
class PolynomTest {
	

	static Polynom expected_Poly;
	static Polynom acutal_Poly;
	static double expectedVaule;
	static double acutalVaule;
	


	@BeforeEach
	void setUp() throws Exception {

		expected_Poly = new Polynom(new Monom(2,3));
		expected_Poly.add(new Monom(4,2));
		expected_Poly.add(new Monom(-1,4));

		acutal_Poly = new Polynom(new Monom(2,3));
		acutal_Poly.add(new Monom(4,2));
		acutal_Poly.add(new Monom(-1,4));

		expectedVaule = 0;
		acutalVaule = 0;
	}

	@Test
	void testPolynom() {

		acutal_Poly = new Polynom();

		if(!acutal_Poly.isZero()){
			fail("Your got Worng with the init Polynom, this default init need to be with 0 Monoms.");
		}
	}

		@Test
		void testPolynomMonom() {
			Monom m1 = new Monom(4,6);
			acutal_Poly = new Polynom(m1);
			String expctedString = "[4.0x^6]";
			String acutalString = acutal_Poly.toString();
			if(!expctedString.equals(acutalString))
				fail("Your got Worng with the adding Monom in the Constractor");
		
		}

	
	@Test
	void testPolynomPolynom() throws wrongDataException {

		Polynom Poly1 = new Polynom("2x^3+5x");
		expected_Poly = new Polynom ("2x^3+5x");
		acutal_Poly = new Polynom(Poly1);

		if(!acutal_Poly.equals(expected_Poly))

			fail("Your got Worng with the Copy init");
	}

		@Test
		void testPolynomString() throws wrongDataException {
			
			String expected_String = "[5.0x^5, -3.0x^2, 2.0x^1]";
			acutal_Poly = new Polynom("2x+5x^5-3x^2");
			
			String acutal_String = acutal_Poly.toString();

			if(!expected_String.equals(acutal_String))
			fail("Your got Worng with the String constractor.");
		}

	@Test
	void testF() {
		double sum = 0;
		double x = 2;
		acutalVaule = acutal_Poly.f(x);
		Iterator<Monom> Monoms = expected_Poly.iteretor();

		while(Monoms.hasNext()) {
			sum = sum + (Monoms.next().f(x));
		}

		if(acutalVaule != sum)
			fail("Not yet implemented");
	}

	@Test
	void testAddPolynom_able() throws wrongDataException {
		Polynom poly = acutal_Poly;  // poly = -1.0x^4, 2.0x^3, 4.0x^2
		expected_Poly = new Polynom("-2.0x^4, 4.0x^3, 8.0x^2");
		acutal_Poly.add(poly); // adding the same polynom

		if(!expected_Poly.equals(acutal_Poly))
			fail("You got Worng with adding polynom.. check your add monom/your add polynom code.");

	}

	@Test
	void testAddMonom() throws wrongDataException {

		expected_Poly = new Polynom("-1.0x^4, 5.0x^3, 4.0x^2"); // adding 3x^3 then 2x^3+3x^3 = 5x^3
		acutal_Poly.add(new Monom(3,3));

		if(!expected_Poly.equals(acutal_Poly))
			fail("You got worng with adding monom with the same power");

		acutal_Poly.add(new Monom(3,5)); // adding with differnt power, then i need to be added to the Polyom.
		expected_Poly = new Polynom("3.0x^5, -1.0x^4, 5.0x^3, 4.0x^2"); // adding 3x^3 then 2x^3+3x^3 = 5x^3	

		if(!expected_Poly.equals(acutal_Poly))
			fail("You got worng with adding monom with the differnt power");


	}

	@Test
	void testSubstract() throws wrongDataException {
		Polynom poly = new Polynom("-2.0x^4+ 3.0x^3+5.0x^2"); 
		acutal_Poly.substract(poly); // acutal_Poly - poly.
		expected_Poly = new Polynom("1.0x^4, -1.0x^3, -1.0x^2"); // this is the result of the substract.


		if(!expected_Poly.equals(acutal_Poly))
			fail("You got worng with the substract");


	}

	@Test
	void testMultiply() throws wrongDataException {

		Polynom poly = new Polynom("2x^2+x");
		acutal_Poly.multiply(poly);  // (-1.0x^4, 2.0x^3, 4.0x^2) * (2x^2+x) = -2.0x^6, 3.0x^5, 10.0x^4, 4.0x^3
		expected_Poly = new Polynom("-2.0x^6, 3.0x^5, 10.0x^4, 4.0x^3");

		if(!expected_Poly.equals(acutal_Poly))
			fail("You got worng with the multiply function!");
	}

	@Test
	void testEqualsPolynom_able() {
		// my acutal and expected polyom are the same.
		if(!acutal_Poly.equals(expected_Poly))
			fail("You got worng with the equal function, check your iterator and the checks.");
	}

	@Test
	void testIsZero() {
		acutal_Poly = new Polynom();

		if(!acutal_Poly.isZero())
			fail("Your got Worng with the zero polynom check.");
	}
	@Test
	void testRoot() throws wrongDataException {
		acutal_Poly = new Polynom("2x^2+4x");
		acutalVaule = acutal_Poly.root(-1, 2, 0.1); // the roots in this range [-1,2] is 0.
		if(acutalVaule != 0.03125) // this is in numery sysyem.. its close to abs zero.
			fail("You Got worng with the root system.");
	}

	@Test
	void testCopy() throws wrongDataException {

		expected_Poly = new Polynom("2x^2+3x");
		acutal_Poly = (Polynom) expected_Poly.copy();

		if(!expected_Poly.equals(acutal_Poly))
			fail("Your Got worng with the copy polynom.");
	}

	@Test
	void testDerivative() throws wrongDataException {
		expected_Poly = new Polynom("-4x^3+6x^2+8x"); // the Derivative is: [-4x^3 +6x^2 +8x].
		acutal_Poly = (Polynom) acutal_Poly.derivative();

		if(!acutal_Poly.equals(expected_Poly))
			fail("Your Got worng with the Derivative function!");
	}

	@Test
	void testArea() throws wrongDataException {

		acutal_Poly = new Polynom("2x^2+4x"); // there is no area above x-eis.
		acutalVaule = acutal_Poly.area(-1, 0, 0.001); // the solution sholud be 0.
		expectedVaule = 0;
		if(expectedVaule !=acutalVaule)
			fail("Your got problem with calculate area.. the soulotion sholud be 0.");

		acutal_Poly = new Polynom("x^3-2x"); // this is a double function.. then the area cant be 0! cuse we clatule above x-eis
		acutalVaule = acutal_Poly.area(-1.414, 1.414, 0.001); // the area sholud be 0.9999..
		expectedVaule = acutal_Poly.area(-1.414, 0, 0.001); // from [0, 1.414] the area is negative, i wont calculte that anyway.

		if(acutalVaule != expectedVaule) // the number should be equals.
			fail("Your got problem with calculate area.. the area need to be the same.");

	}

	@Test
	void testIteretor() {

		Iterator<Monom> expected = expected_Poly.iteretor();

		if(expected == null) 
			fail("Your got worng with creat a interator.");


	}
	
	@Test
	void underX_area() throws wrongDataException {
		acutal_Poly = new Polynom("x^3-2x");
		acutalVaule  = acutal_Poly.underX_area(-1.414, 0, 0.01); // there is no area.. should be 0.
		expectedVaule = 0;
		
		if (acutalVaule != expectedVaule) {
			fail("You got Worng with calculate the area under x.");
		}
		
		
		
		
	}
	@Test
	void testToString() {
		String expectedString = "[-1.0x^4, 2.0x^3, 4.0x^2]";
		String acutalString = acutal_Poly.toString();
		
		if(!expectedString.equals(acutalString))
		fail("Your got worng with the ToString function.");
	}

}

