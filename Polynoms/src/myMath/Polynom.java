package myMath;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;

import Exceptions.wrongDataException;
import fr.julien.graphique.Graphique;
import fr.julien.graphique.ZoneGraphique;
import fr.julien.graphique.axes.AxeX;
import fr.julien.graphique.axes.AxeY;
import fr.julien.graphique.axes.OptionAxe;
import fr.julien.graphique.element.fonction.Fonction;
import fr.julien.graphique.element.forme.Polygone;
import fr.julien.graphique.element.point.Point;
import fr.julien.graphique.element.quadrillage.Quadrillage;
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
	ArrayList<Point> Point_list =  new ArrayList<>();




	//****************** Constructors ******************
	/**
	 * Constractor of Polynom which defaults to adds an Zero Monom
	 */
	public Polynom() {
		this.Monoms_list.add(new Monom(0,0));
	}

	public Polynom(Monom m) {

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
	 * @throws wrongDataException 
	 */
	public Polynom(String string) throws wrongDataException {	
		this();
		PolytoString(string);		

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

		Iterator<Monom> Monoms = p1.iteretor();

		while(Monoms.hasNext()) {

			Monom temp = Monoms.next();
			this.add(temp);		
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


		double c = x0;

		if((f(x0)*f(x1)) >= 0) {
			System.out.println("There is no solotion for this polynom.");
			return Double.MAX_VALUE;
		}
		else {
			while ((x1 - x0) >= eps) {			
				c = (x0+x1)/2;
				if (f(c) == 0.0) {
					break;
				}
				else if (f(c)*f(x0) < 0) {
					x1 = c;
				}
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


	/**
	 *   Printed a Polynom in the following style: "Polynom :2.0x^5, 6.0x^3, 5.0x^2, 4.0"
	 @return String in the form of a Polynom  
	 */
	public String toString() {
		return Monoms_list.toString();

	}

	@Override
	public double underX_area(double x0, double x1, double eps) {	
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
			if (f(x0) <= 0) {
				double area_By_Parts = ((f(x0)+f(x0+eps))/2)*eps;
				sum = sum + area_By_Parts;
			}
		}
		return Math.abs(sum);
	}


	@Override
	public void PrintMinMax(String s, double x0, double x1) {

		double start = x0;
		double end = x1;


		Polynom der = (Polynom) this.derivative();
		double eps = 0.0001;
		for (;start < end ; start=start+eps) {
			if(der.f(start) < 0 && der.f(start+eps) > 0) {
				Point_list.add(new Point(start,this.f(start)));
			}
			if(der.f(start) > 0 && der.f(start+eps) < 0) {
				Point_list.add(new Point(start,this.f(start)));
			}

		}
		Draw_MinMax(s,Point_list,x0,x1);

	}

	private static void Draw_MinMax(String s, ArrayList<Point> point_list, double x0, double x1) {

		JFrame f = new JFrame();		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		OptionAxe optionsAxes = new OptionAxe(Color.BLACK, true, -1, 1, true, true);
		Graphique.getInstance().initGraphique(new AxeX(x0, 10, optionsAxes), new AxeY(-10, x1, optionsAxes));
		char a = 'A';
		for (int i = 0 ; i < point_list.size(); i++, a++) {
			Graphique.getInstance().ajouterElement(new Point(a, point_list.get(i).getAbscisse(),point_list.get(i).getOrdonnee()));
		}
		Graphique.getInstance().ajouterElement(new Fonction((s)));
		List<Point> points = new ArrayList<Point>();
		Graphique.getInstance().ajouterElement(new Polygone(points));
		Graphique.getInstance().ajouterElement(new Quadrillage(0.5, 0.5));
		f.add(new ZoneGraphique());
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);

	}
	private void PolytoString(String string) throws wrongDataException {
		boolean is_coefficient = true;
		String coefficient = "" , power = "";
		// replace +- to -
		string = string.replaceAll("\\+-", "-");
		// remove spaces
		string = string.replaceAll(" ", "");
		// remove [ . . . ] 
		string = (string.charAt(0) =='[' && string.charAt(string.length()-1) ==']') ? string.substring(1, string.length()-1) : string;
		for (int i = 0; i < string.length(); i++)
		{	
			// now in coefficient
			if (is_coefficient)
			{
				// not last char
				if (i+1<string.length())
				{
					// end of coefficient because of x X *
					if(string.charAt(i) =='x' || string.charAt(i) =='*' || string.charAt(i) =='X')	
					{
						//if my coefficient is blank so it means the the coefficient is 1
						if (coefficient.equals("") || coefficient.equals("-"))
						{
							coefficient += "1";
						}
						// i already have an x and the + or - means that i dont have a power so it means we are at power 1
						if(string.charAt(i+1)=='+' || string.charAt(i+1) == '-')
						{
							add(new Monom(Double.parseDouble(coefficient),1));
							coefficient = "";
							power = "";
						}
						// i already have an x		
						// regular done with coefficient so we are moving to power so coefficient is now false
						else
						{
							is_coefficient = false;
						}
					}
					// not x nor X nor *
					// if next value is + or - so no x's and its a monom where x^0
					else if (string.charAt(i+1)=='+' || string.charAt(i+1) == '-')
					{
						add(new Monom(Double.parseDouble(coefficient + string.charAt(i)), 0));
						coefficient = "";
					}
					// not x nor X nor * nor + nor -
					// if this value is - or . or digit so add it to coefficient	
					else if((Character.isDigit(string.charAt(i))|| string.charAt(i) == '-' || string.charAt(i) == '.'))
					{
						coefficient = coefficient + string.charAt(i);
					}
					// number starting is + so do nothing
					else if (string.charAt(i) == '+')
					{

					}
					// all other cases where this value is not valid
					else
					{
						throw new wrongDataException("A polynom can not have a not number value =  " + string.charAt(i) + " at location " + i);
					}
				}
				// this is the last char in the string
				// no need the if since its only case that its not "i+1<string.length()" so it would be easier to debug
				else if (i+1==string.length())
				{	
					//  when last char is x or X 
					if(string.charAt(i) =='x' || string.charAt(i) =='X')		
					{
						// make sure coefficient has a value
						if (coefficient.equals("") || coefficient.equals("-"))
						{
							coefficient += "1";
						}
						add(new Monom(Double.parseDouble(coefficient), 1));	
					}
					// when last char is number
					else if (Character.isDigit(string.charAt(i)))
					{
						add(new Monom(Double.parseDouble(coefficient + string.charAt(i)), 0));	
					}
					// all other cases where this value is not valid
					else
					{
						throw new wrongDataException("A polynom can not have a not number value =  " + string.charAt(i) + " at location " + i);
					}
				}
			}
			// now in power
			else
			{

				// not last char
				if (i+1<string.length())
				{
					// making sure that the beginning of power is written correctly
					if (string.charAt(i) == '^' && !Character.isDigit(string.charAt(i+1)) || ((string.charAt(i) == 'x' || string.charAt(i) == 'X') && (string.charAt(i+1) != '^' && string.charAt(i+1) != ',')))
					{
						throw new wrongDataException("A power can not be a negitave power or a random symbal at location " + (i+1) + string.charAt(i) );
					}
					// when the string is presented as the toString value
					// for example "3.0*X^8, -8.0*X^3, 1.1*X, -3.0" 
					// we will need to skip the 2 values ", " and this means we are done with this monom
					if (string.charAt(i+1) ==',')
					{
						add(new Monom(Double.parseDouble(coefficient),(power=="" && (string.charAt(i)=='x' || string.charAt(i)=='X')) ? 1 :Integer.parseInt(power+ string.charAt(i))));
						coefficient = "";
						power = "";
						is_coefficient = true;
						i+=1;
					}
					// please notice && 
					// this case when the power is - we throw an exception
					else if (string.charAt(i+1) == '-' && string.charAt(i)=='^')
					{
						throw new wrongDataException("A power can not be a negitave power at location " + i );
					}
					// if next value is + or - it means we are done with this monom
					else if(string.charAt(i+1)=='+' || string.charAt(i+1) == '-' )
					{
						add(new Monom(Double.parseDouble(coefficient),Integer.parseInt(power+ string.charAt(i))));
						coefficient = "";
						power = "";
						is_coefficient = true;
					}
					// if we are not - nor + 
					// and we are a digit so add it to the power
					else if (Character.isDigit(string.charAt(i)))				
					{
						power = power + string.charAt(i);
					}
					else if (string.charAt(i) == 'x' || string.charAt(i) == 'X' || string.charAt(i) == '^')
					{

					}
					else if (string.charAt(i) == ',' && power =="")				
					{
						add(new Monom(Double.parseDouble(coefficient),1));
						coefficient = "";
						power = "";
						is_coefficient = true;			
					}
					// if not number 
					// all other cases where this value is not valid
					else
					{
						throw new wrongDataException("A polynom can not have a not number value =  " + string.charAt(i) + " at location " + i);
					}
				}
				// this is the last char in the string
				// no need the if since its only case that its not "i+1<string.length()" so it would be easier to debug
				else if (i+1==string.length())
				{
					// making sure last char is a digit
					if (Character.isDigit(string.charAt(i)))
					{
						add(new Monom(Double.parseDouble(coefficient), Integer.parseInt(power+ string.charAt(i))));
					}
					// all other cases where this value is not valid
					else
					{
						throw new wrongDataException("A polynom can not have a not number value = '" + string.charAt(i) + "' at location " + i);
					}
				}
			}
		}
	}



}
