
package myMath;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Omer Paz,Shimon Mimoun
 *
 */



public class Monom implements function{

	//****************** Constructors ******************

	/**
	 * Constructor who creates an object of the class 
	 * @param a this is the parameter that defines my coefficient of the Monom for exemple 3*x^4 as a is 3
	 * @param b this is the parameter that defines the powerof the Monom for exemple 3*x^4 as a is 4
	 */
	public Monom(double a, int b) {

		this.set_coefficient(a);
		this.set_power(b);

	}
	/**
	 * Copy Constructor of Monom 
	 * @param ot receives an object Monom and the copy in our initial object
	 */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}

	//****************** Public Getters ******************

	/** 
	 * Method that returns the Coefficient of a function 
	 * @return the value of the coefficient 'a' of my function
	 */
	public double get_coefficient() {

		return this._coefficient;
	}
	/**
	 * Method that returns the Power of a function 
	 * @return the value of the Power 'b' of my function
	 */
	public int get_power() {
		return this._power;
	}


	//****************** Private Methods and Data *****************
	/**
	 * Method that change the Power of a Monom 
	 * @param a Input a coefficient 'a' in the Object 
	 */
	private void set_coefficient(double a){

		this._coefficient = a;
	}

	/**
	 *Method that change the Power of a Monom , the method only accepts positive numbers.If the user enters a negative value, by default the value enter will be 1.
	 * @param p Input a Power 'p' in the Object
	 */
	private void set_power(int p) throws RuntimeException {

		if(p < 0) {
			throw new RuntimeException("got negative power");

		}
		this._power = p;
	}



	private double _coefficient; 
	private int _power;

	// ***************** add your code below **********************


	public double f(double x) {

		double a = this.get_coefficient();
		int b = this.get_power();

		double pow_result = Math.pow(x, b);

		double ans = a*pow_result;

		return ans;
	}

	/**
	 * Calculates the derivative of the given function.For more information on the derivatives :https://en.wikipedia.org/wiki/Derivative
	 * @return The derivative of the function f
	 */
	public Monom derivative() {
		Monom ansMonom;


		if (this._power == 0) {
			ansMonom = new Monom(0,0);
			return ansMonom;
		}else
			ansMonom = new Monom(this._power*this._coefficient,this._power-1);
		return ansMonom;

	}
	/**
	 * Adding between two Monoms, only if their powers are equal.
	 * 
	 * @param m Is a Monom with which i want to make the adding.
	 */
	public void add (Monom m) {

		if (this.isZeroMonom()) {
			this._coefficient =+ m._coefficient;
			this._power =+ m._power; 
		}
		else if (this._power == m._power) {
			this._coefficient += m._coefficient;
		}
		else{
			System.out.println("Unable to add two Monoms if their power are different");
		}
	}

	/**
	 * Multiply between two Monoms. 
	 * @param m Is a Monom with which i want to make the Multiply.
	 * @return A Monom of the multiplication of my two Monoms.
	 */
	public Monom multiply (Monom m) {
		Monom ansMonom;

		if (this.isZeroMonom() || m.isZeroMonom()) {
			ansMonom = new Monom(0,0);
			return ansMonom;
		}
		if (this._power == 0) {
			ansMonom = new Monom(this._coefficient*m.get_coefficient(),m.get_power());
			return ansMonom;
		}else if(m._power == 0) {
			ansMonom = new Monom(this._coefficient*m.get_coefficient(),this._power);
			return ansMonom;
		}
		ansMonom = new Monom(this._coefficient*m.get_coefficient(), this._power+m.get_power());
		return ansMonom;
	}
	/**
	 * Check if a Monom is 0.
	 * @return True if a Monom is 0 else False.
	 */
	public boolean isZeroMonom() {
		return this._coefficient == 0;
	}

	public boolean equals(Monom m) {
		if(this.get_coefficient() == 0 && m.get_coefficient() == 0) {
			return true;
		}
		return (this.get_coefficient() == m.get_coefficient() && this.get_power() == m.get_power());
	}

	/**
	 * Printed a Monom in the following style: "Monom: 2.0x^9"
	 @return String in the form of a Monom 
	 */
	public String toString () {
		if (this._power == 0) {
			return this._coefficient+"";
		}
		if (this._coefficient == 0) {
			return 0+"";
		}
		else
			return this._coefficient+"x^"+this._power;

	}

}
