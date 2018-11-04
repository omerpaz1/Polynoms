package myMath;
/** This interface represents a simple function of type y=f(x), where both y and x are real numbers.
 *@author Omer Paz,Shimon Mimoun
 *
 */
public interface function {
	/**
	 *Calculates the value of function in the values x
	 * @param x Is the entry entered into the function f
	 * @return The value of the function at a point x ,example: f(x) =2*x^2 ; f(2)=8
	 */
	public double f(double x);
}
