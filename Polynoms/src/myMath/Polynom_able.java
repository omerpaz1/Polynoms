package myMath;

import java.util.Iterator;

/**
 * This interface represents a general Polynom: f(x) = a_1X^b_1 + a_2*X^b_2 ... a_n*Xb_n,
 * where: a_1, a_2 ... a_n are real numbers and b_1<b_2..<b_n >=0 are none negative integers (naturals)
 * For formal definitions see: https://en.wikipedia.org/wiki/Polynomial
 * 
 * @author Omer Paz,Shimon Mimoun
 *
 */
public interface Polynom_able extends cont_function{
	/**
	 * Add to Polynom the Polynom P1.
	 * @param p1 is  Polynom that i want to add to the current Polynom.
	 */
	public void add(Polynom_able p1);
	/**
	 * Add to this Polynom the Monom m1.	
	 * @param m1 is Monom that i want to add to the current Polynom 
	 */
	public void add(Monom m1);
	/**
	 * Subtract p1 from the current Polynom
	 * @param p1 is Polynom that i want to Subtract to the current Polynom 
	 */
	public void substract(Polynom_able p1);
	/**
	 * Multiply this Polynom by p1
	 * @param p1 is Polynom that i want to Multiply to the current Polynom 
	 */
	public void multiply(Polynom_able p1);
	/**
	 * Test if this Polynom is logically equals to a Polynom p1.
	 * @param p1 is Polynom that i want to equals to the current Polynom 
	 * @return true if this Polynom represents the same function as p1
	 */
	public boolean equals (Polynom_able p1);
	/**
	 * Test if this is the Zero Polynom
	 * @return True is a Zero Polynom else false 
	 */
	public boolean isZero();
	
	
	/**
	 * How to calculate root with bisection method. 
	 * Compute a value x' (x0<=x'<=x1) for with |f(x')| < eps assuming (f(x0)*f(x1)<=0, returns f(x2) such that: * (i) x0<=x2<=x2 && (ii) f(x2)
	 * 
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps step (positive) value
	 * @return the value closest to the function on the x-axis
	 */
	public double root(double x0, double x1, double eps);
	/**
	 * Create a deep copy of this Polynom 
	 * @return Deep copy of Polynom original
	 */
	public Polynom_able copy();
	
	/**
	 * Calculates the derivative of the given function.For more information on the derivatives :https://en.wikipedia.org/wiki/Derivative
	 * @return new Polynom after a derivative
	 */
	public Polynom_able derivative();

	/**
	 * Compute Riemann's Integral over this Polynom starting from x0, till x1 using eps size steps,
	 * see: https://en.wikipedia.org/wiki/Riemann_integral
	 * @return the approximated area above the x-axis below this Polynom and between the [x0,x1] range.
	 */
	public double area(double x0,double x1, double eps);
	/**
	 * An Iterator (of Monoms) over this Polynom
	 * @return  Iterator specific of the array 
	 */
	public Iterator<Monom> iteretor();
	
	
	/**
	 * Compute Riemann's Integral over this Polynom starting from x0, till x1 using eps size steps,
	 * see: https://en.wikipedia.org/wiki/Riemann_integral
	 * @param x0 starting point
	 * @param x1 end point
	 * @param eps positive step value
	 * @return the approximated area under X axis below this Polynom and between the [x0,x1] range.
	 */
	public double underX_area(double x0 , double x1 , double eps);
	
	/**
	 * Search for Minimum and Maximum points, and add them to the Graphic Drawing.
	 * @param s  Receiv String in the form of a Polynom
	 * @param x0 lower definition domain
	 * @param x1 higher definition domain
	 * @return Printing of the Minimum and Maximum Points on the Graph
	 */
	public void PrintMinMax(String s, double x0, double x1);

	
}
