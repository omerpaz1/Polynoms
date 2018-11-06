package myMath;

import java.util.ArrayList;
import java.util.Iterator;

import fr.julien.graphique.element.point.Point;
import myMath.Monom;


/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Omer Paz,Shimon Mimoun
 *
 */
public class Polynom implements Polynom_able{


	//****************** Private Methods and Data *****************


	private ArrayList<Monom> Monoms_list=  new ArrayList<>();
	private Monom_Comperator  MonomSort = new Monom_Comperator();
	private Point max;
	private Point min;


	//****************** Constructors ******************
	/**
	 * Constractor of Polynom which defaults to adds an Zero Monom
	 */
	public Polynom() {
		this.Monoms_list.add(new Monom(0,0));
		this.max = new Point(0,0);
		this.min = new Point(0,0);

	}

	public Polynom(Monom m) {
		this();
		this.Monoms_list.add(m);
	}
	/**
	 * Create a Polynom by inserting a Polynom
	 * 
	 * @param poly is Polynom which serves to create a new Polynom
	 */
	public Polynom(Polynom poly) {
		this();
		Polynom ans = (Polynom) poly.copy();
		this.Monoms_list = ans.Monoms_list;
	}

	/**
	 * Create a Polynom by inserting a String in a format to Polynom and the convert to Polynom , and also allows us to receive a String which will then be checked if it is in the desired form
	 * @param string Get a String in the form of a Polynom
	 */
	public Polynom(String string) {	
		this();
		PolytoString(string);		

	}

	//****************** Getters ******************

	public double getMax() {
		return (double)this.max.getY();
	}



	//****************** Functions *****************


	/**
	 * This function calculate the value of y by giving x from the user of this polynom.
	 * @param x Is the entry entered into the function f
	 */
	public double f(double x) {
		double sum = 0;
		Iterator<Monom> Monoms = iteretor();

		while (Monoms.hasNext()) {

			Monom temp = Monoms.next();

			sum = sum + temp.f(x);
		}
		return sum;

	}



	@Override
	/**
	 * Function that allows us to add a Polynom to our current Polynom
	 */
	public void add(Polynom_able p1) {

		Iterator<Monom> Monoms = iteretor();

		while(Monoms.hasNext()) {

			Monom temp = Monoms.next();
			p1.add(temp);		
		}

	}

	/**
	 * Function that allows us to add a Monom to our current Polynom and sort them
	 */
	public void add(Monom m1) {

		Iterator<Monom> Monoms = this.iteretor();		

		while(Monoms.hasNext()) {
			Monom temp = Monoms.next();
			if(temp.isZeroMonom()) {
				temp.add(m1);
				return;
			}
			if (temp.get_power() == m1.get_power()) {; // need to fix
			temp.add(m1);
			if(temp.isZeroMonom()) {
				Monoms_list.remove(temp);
				if(Monoms_list.isEmpty()) {
					Monoms_list.add(new Monom(0,0));
				}
			}
			return;
			}
		}
		Monoms_list.add(m1);
		Monoms_list.sort(MonomSort);

	}



	@Override
	/**
	 * Function that allows us to substract a Polynom to our current Polynom
	 */
	public void substract(Polynom_able p1) {
		Iterator<Monom> Monoms = p1.iteretor();		

		while (Monoms.hasNext()) {

			Monom temp_Monom = Monoms.next();

			this.add(new Monom(0-temp_Monom.get_coefficient(), temp_Monom.get_power()));
		}


	}

	@Override
	/**
	 * Function that allows us to multiply a Polynom to our current Polynom
	 */
	public void multiply(Polynom_able p1) {

		Iterator<Monom> poly = iteretor();		
		Polynom ans = new Polynom();

		if (!this.isZero() || !p1.isZero()) {

			while(poly.hasNext()) {
				Monom tempPoly = poly.next();

				Iterator<Monom> poly1 = p1.iteretor();		

				while(poly1.hasNext()) {

					Monom tempPoly1 = poly1.next();

					ans.add(tempPoly.multiply(tempPoly1));		
				}

			}
			this.Monoms_list = ans.Monoms_list;
		}else
			this.Monoms_list = ans.Monoms_list;
	}

	@Override
	/**
	 *  Gets a given polynom and equals its monoms and checks if the monoms are the same as in this polynom
	 *
	 */
	public boolean equals(Polynom_able p1) {
		boolean flag = true;
		Iterator<Monom> poly = this.iteretor();		
		Iterator<Monom> p1_poly = p1.iteretor();		

		while(poly.hasNext() &&  p1_poly.hasNext() && flag ) {

			Monom tempPoly1 = poly.next();
			Monom tempPoly2 = p1_poly.next();

			if ((tempPoly1.get_coefficient() == tempPoly2.get_coefficient()) && (tempPoly1.get_power() == tempPoly2.get_power())) {
				flag = true;
			}
			else
				return false;
		}
		return flag;

	}

	@Override
	public boolean isZero() {

		Iterator<Monom> poly = this.iteretor();		

		return (poly.next().get_coefficient() == 0);


	}

	@Override
	public double root(double x0, double x1, double eps) {

		double f_x0 = f(x0);
		double f_x1 = f(x1);
		double c = x0;

		if(f_x0 * f_x1 >= 0) {

			System.out.println("There is no solotion for this polynom.");
			return Double.MAX_VALUE;
		}
		else {
			while ((x1 - x0) >= eps) {			
				c = (x1+x0)/2;

				if (f(c)*f_x0 < 0) 
					x1 = c;
				else {
					x0 = c;
				}

			}
		}
		return c;
	}

	@Override
	public Polynom_able copy() {

		Iterator<Monom> poly = iteretor();		
		Polynom ans = new Polynom(new Monom(0,0));

		while(poly.hasNext()) {
			Monom temp = poly.next();

			ans.add(new Monom(temp));
		}

		return ans;
	}

	@Override
	public Polynom_able derivative() {
		Polynom ans = new Polynom();
		Iterator<Monom> poly = this.iteretor();		

		while(poly.hasNext()) {

			Monom temp = poly.next();

			ans.add(new Monom(temp.derivative()));
		}

		return ans;
	}


	@Override
	public double area(double x0, double x1, double eps) {
		double sum = 0;

		if (x0 == x1) {
			return sum;
		}
		if (x1 < x0) {
			double temp = x1;
			x1 = x0;
			x0=temp;
		}

		for (; x0 < x1; x0 = x0+eps) {
			if (f(x0) >= 0) {
				double area_By_Parts = ((f(x0)+f(x0+eps))/2)*eps;
				sum = sum + area_By_Parts;
			}
		}
		return sum;
	}



	@Override
	public Iterator<Monom> iteretor() {
		return Monoms_list.iterator();
	}


	private void PolytoString(String string) {
		boolean first = true;
		String coefficient = "" , power = "";

		for (int i = 0; i < string.length(); i++)
		{		
			if(string.charAt(i) =='x' || string.charAt(i) =='*' || string.charAt(i) =='X')	
			{
				if (coefficient.equals("") || coefficient.equals("-"))
				{
					coefficient += "1";
				}
				if (i+1<string.length())
				{
					if(string.charAt(i+1)=='+' || string.charAt(i+1) == '-')
					{
						add(new Monom(Double.parseDouble(coefficient),1));
						coefficient = "";
						power = "";
					}
					else
					{
						first = false;
					}
				}
				else
				{
					add(new Monom(Double.parseDouble(coefficient),1));
				}
			}
			else if (first)
			{
				if (i+1<string.length())
				{
					if (string.charAt(i+1)=='+' || string.charAt(i+1) == '-')
					{
						add(new Monom(Double.parseDouble(coefficient + string.charAt(i)), 0));
						coefficient = "";
					}
					else if((Character.isDigit(string.charAt(i))|| string.charAt(i) == '-' || string.charAt(i) == '.'))
					{
						coefficient = coefficient + string.charAt(i);
					}
				}
				else if (i+1==string.length())
				{	
					add(new Monom(Double.parseDouble(coefficient + string.charAt(i)), 0));	
				}
			}
			else
			{
				if (i+1<string.length())
				{
					if (string.charAt(i+1) ==',')
					{
						add(new Monom(Double.parseDouble(coefficient),Integer.parseInt(power+ string.charAt(i))));
						coefficient = "";
						power = "";
						first = true;
						i+=2;
					}
					else if(string.charAt(i+1)=='+' || string.charAt(i+1) == '-' )
					{
						add(new Monom(Double.parseDouble(coefficient),Integer.parseInt(power+ string.charAt(i))));
						coefficient = "";
						power = "";
						first = true;
					}
					else if (Character.isDigit(string.charAt(i)))				
					{
						{
							power = power + string.charAt(i);
						}
					}
				}
				else if (i+1==string.length())
				{
					add(new Monom(Double.parseDouble(coefficient), Integer.parseInt(power+ string.charAt(i))));
				}
			}
		}
	}




	/**
	 *   Printed a Polynom in the following style: "Polynom :2.0x^5, 6.0x^3, 5.0x^2, 4.0"
	 @return String in the form of a Polynom  
	 */
	public String toString() {
		return Monoms_list.toString();

	}

	@Override
	public double underX_area(double x0, double x1, double eps) {
		// Write your code here, use the Area function in polynom.. its Similar
		// remember we need to calculate the area under X axis and above the function.
		// call me if you have problem.		
		return 0;
	}

	@Override
	public void maxMin_Polynom(double x0, double x1, double eps) {
		double y = f(x0);
		double x = f(x0);
		double max = f(x0);
		//double min = x1;

		for (; x0 < x1; x0=x0+eps) {

			if (f(x0) > max) {
				y = f(x0);
				x = x0;
				max = f(x0);
				System.out.println(f(x0)+eps);
				System.out.println("f(x0): "+f(x0)+ "f(x0)+eps: "+f(x0)+eps);
			}else if(max > f(x0)+eps) {
				break;
			}
	

		}

		System.out.println(max);
		System.out.println("x is: "+x);
		System.out.println("y is: "+y);
		Point maxPoint = new Point(x,y);
	}
}