package TestsMyMath;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

import myMath.Monom;
import org.junit.jupiter.api.Test;

import Exceptions.wrongDataException;

class MonomTest {


	static Monom expected_Monom;
	static Monom actual_Monom;
	static double expectedVaule;
	static double actualValue;


	@BeforeEach
	void setUp() throws Exception {
		expected_Monom = new Monom(2,3);
		actual_Monom = new Monom(2,3);
		expectedVaule = 0;
		actualValue = 0;


	}

	@Test
	void testMonomDoubleInt() {

		if(expected_Monom.get_coefficient() != actual_Monom.get_coefficient()) 
			fail("You got worng with the coefficient init");

		if(expected_Monom.get_power() != actual_Monom.get_power()) 
			fail("You got worng with the power init");

	}

	@Test
	void testMonomMonom() {

		Monom Monom_Copy = new Monom(actual_Monom);

		if(Monom_Copy.get_coefficient() != actual_Monom.get_coefficient())
			fail("You got a worng coefficient");
		if(Monom_Copy.get_power() != actual_Monom.get_power())
			fail("You got a worng Power");
	}

	@Test
	void testGet_coefficient() {

		expectedVaule = expected_Monom.get_coefficient();

		if(actual_Monom.get_coefficient() != expectedVaule)
			fail("Your got worng with your coefficient/the return");

	}

	@Test
	void testGet_power() {

		expectedVaule = expected_Monom.get_power();

		if(actual_Monom.get_power() != expectedVaule)
			fail("Your got worng with your coefficient/the return");

	}

	@Test
	void testF() {

		double x = 3;

		expectedVaule = actual_Monom.get_coefficient()*Math.pow(x, actual_Monom.get_power()); 

		if(expectedVaule != actual_Monom.f(x))
			fail("Your got worng with the f fanction");

	}

	@Test
	void testDerivative() {
		expected_Monom = new Monom (actual_Monom.get_power()*actual_Monom.get_coefficient(),actual_Monom.get_power()-1);
		actual_Monom = actual_Monom.derivative();

		if(actual_Monom.get_coefficient() != expected_Monom.get_coefficient())
			fail("Your got Worng with the coefficient after the Derivative");


		if (actual_Monom.get_power() != expected_Monom.get_power())
			fail("Your got Worng with the power after the Derivative");

	}

	@Test
	void testAdd() {

		Monom Monom1 = new Monom(1,3);
		expected_Monom = new Monom (actual_Monom.get_coefficient()+Monom1.get_coefficient(), actual_Monom.get_power());
		actual_Monom.add(Monom1);

		if(actual_Monom.get_coefficient() != expected_Monom.get_coefficient()) 
			fail("Your got Worng with the Adding between the coefficients");
	}

	@Test
	void testMultiply() {

		Monom Monom1 = new Monom(2,5);
		expected_Monom = new Monom(Monom1.get_coefficient()*actual_Monom.get_coefficient(),Monom1.get_power()+actual_Monom.get_power());
		actual_Monom = actual_Monom.multiply(Monom1);

		if(actual_Monom.get_coefficient() != expected_Monom.get_coefficient())
			fail("Your got Worng with the multiply between the coefficients");

		if(actual_Monom.get_power() != expected_Monom.get_power())
			fail("Your got Worng with the Adding between the powers");

	}

	@Test
	void testIsZeroMonom() {

		actual_Monom = new Monom(0,3);

		if(!actual_Monom.isZeroMonom())
			fail("Your got Worng with the the return");
	}

	@Test
	void testToString() {

		String MonomString = "2.0x^3";

		if(!actual_Monom.toString().equals(MonomString))
			fail("Your got Worng with the the return");
	}

}
