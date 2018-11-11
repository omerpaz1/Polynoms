package myMath;

import Exceptions.wrongDataException;

/**
 * this class testing all the functions in Polynom and Monom class's.
 * @author Omer
 * 
 *
 */
public class Test {
	
	public static void main(String[] args) throws wrongDataException {
		
		
		// ***************** Monom Functions **********************
		
		Monom zero_Monom = new Monom(0,0);
		
		//** F(x) Function **
		Monom m1 = new Monom (2,9);
		System.out.println("Monom: "+m1.toString()+" ,F(5)  = "+m1.f(5));
		System.out.println("**************************************************");
		

		
		//** derivative Function **
		
		System.out.println("The Derivative Of: "+m1.toString()+" Monom is: "+m1.derivative());
		System.out.println("**************************************************");

		
		//** Add Funcotion **
		
		  	// if the powers are the same, we can add between monoms.
		Monom m2 = new Monom(2,9);
		Monom m3 = new Monom(5,4);

		System.out.print("Monom: "+m1.toString()+" + "+m2.toString()+" = ");
		m1.add(m2);
		System.out.println(m1.toString());
		System.out.print("Monom: "+m1.toString()+" + "+m3.toString()+" = ");
		m1.add(m3);
		System.out.println(m1.toString());
			// Add with Zero_Monom:
		
		System.out.print("Monom: "+m1.toString()+" + "+zero_Monom.toString()+" = ");
		m1.add(zero_Monom);
		System.out.println(m1.toString());
		System.out.println("**************************************************");

		
		//** multiply Funcotion **
		
		Monom m_multi1 = new Monom(2,0);
		Monom m_multi2 = new Monom(2,4);
		Monom m_multi3 = new Monom(5,4);
		
		Monom ans_multi1 = m_multi1.multiply(m_multi2);
		System.out.println("Multiply Function 1: "+m_multi1.toString()+" * "+m_multi2.toString()+" = "+ans_multi1.toString());
		
		Monom ans_multi2 = m_multi2.multiply(m_multi3);
		System.out.println("Multiply Function 2: "+m_multi2.toString()+" * "+m_multi3.toString()+" = "+ans_multi2.toString());
		
		Monom ans_multi3 = zero_Monom.multiply(m_multi2); // zero monom = 0;
		System.out.println("Multiply (Zero Monom) Function 3: "+zero_Monom.toString()+" * "+m_multi2.toString()+" = "+ans_multi3.toString());
		System.out.println("**************************************************");

		
		// ***************** Polynom Functions **********************
		
		
		//  *** Constructors ***
		Polynom pol = new Polynom(new Monom(2,3));
		System.out.println("Constructor Polynom that gets heis first Monom: "+pol.toString());
		System.out.println();

		
		Polynom pol2 = new Polynom();
		System.out.println("Default Polynom Constructor will be zero Polynom: "+pol2.toString());
		System.out.println();


		Polynom poly = new Polynom("-3.5+7x^2-5+3x^2-2.6+5.756x-2.3x^21");
		System.out.println("Polynom Constructor that gets heis Polynom by String: "+poly.toString());
		System.out.println("**************************************************");


		
		
		
		// *** Add(Monom) ***

		 	//Show that he sort the polynoms by high power.
			pol.add(new Monom(5,2));
			pol.add(new Monom(4,3));
			pol.add(new Monom(4,0));
			pol.add(new Monom(2,5));

			System.out.println("Polynom 1 = "+pol.toString());
			System.out.println("**************************************************");

		
			// add monoms if they have the same power.
			pol2.add(new Monom(2,3));
			pol2.add(new Monom(5,2));
			pol2.add(new Monom(4,3));
			System.out.println("Polynom 2 = "+pol2.toString());
			System.out.println("**************************************************");

			
			//if all the monoms are the same power.
			Polynom pol3 = new Polynom(new Monom(2,3));
			pol3.add(new Monom(5,3));
			pol3.add(new Monom(4,3));
			pol3.add(new Monom(4,5));

			System.out.println("Polynom 3 = "+pol3.toString());
			System.out.println("**************************************************");

			
		// *** Add(Polynom) Funcotion ***

		
			System.out.print(pol.toString()+" + "+pol2.toString()+" = ");
			pol.add(pol2);
			System.out.println(pol2.toString());

			System.out.println("**************************************************");

			// *** F(x) - Polynom Funcotion ***
			
			// left take pol 1 , he got: [2x^5, 2x^3 , 5x^2]
			// pick x = 2.
			
			System.out.println(pol.toString()+" F(2) = "+pol.f(2));
			System.out.println("**************************************************");

			
			// *** Substract - Polynom Funcotion ***
				// Substract: Polynom 1 - Polynom 3.
				
			
			pol.substract(pol3);
			
			System.out.println("Polynom 1 - Polynom 3 = "+pol.toString());
			System.out.print(pol.toString()+" + "+pol3.toString()+" = ");
			pol.add(pol3);
			System.out.println(pol3.toString());
			System.out.println("**************************************************");

			
			// *** Multiply Polynom Funcotion ***
			
			Polynom pol4 = new Polynom(new Monom(3,4));
			Polynom pol5 = new Polynom(new Monom(2,2));

			pol4.add(new Monom(2,5));
			pol4.add(new Monom(5,2));
			
			
			pol5.add(new Monom(5,3));
			pol5.add(new Monom(6,4));
			

			System.out.print(pol4.toString()+" * "+ pol5.toString()+"= ");
			pol4.multiply(pol5);
			System.out.println(pol4.toString());

			System.out.println("**************************************************");


			// *** Copy Polynom Funcotion ***
			// copy pl4 to the same as pol5;
			System.out.println("Polynom 4 now is: "+pol4.toString());
			pol4 = (Polynom) pol5.copy();
			System.out.println("Polynom 4 copy: "+pol5.toString()+" = "+pol4.toString());
			System.out.println("**************************************************");

			
			// *** equal Polynom Funcotion *** 
			
			System.out.println("Polynom 4 is: "+pol4.toString()+" ,and Polynom 5 is: "+pol5.toString()+" they are equals?: "+pol4.equals(pol5)); // return true cuse pol4 is copy of pol5.
			System.out.println("Polynom 4 is: "+pol4.toString()+" ,and Polynom 3 is: "+pol3.toString()+" they are equals?: "+pol4.equals(pol3)); // return false cuse they are not the same polynom.
			System.out.println("**************************************************");

			// derivative polynom
			System.out.println("Polynom 4 is: "+pol4.toString()+" the derivative is: "+pol4.derivative());
			System.out.println("**************************************************");

			
			// polynom 2 = 
			
			System.out.println("f(x) of: "+pol.toString()+" when x0 = -2: "+pol.f(-2));
			System.out.println("f(x) of: "+pol.toString()+" when x1 = 5: "+pol.f(5));

			System.out.println("the root is: "+pol.root(-2, 5, 0.001));
			System.out.println("**************************************************");

			
			System.out.println("The Polynom is: "+pol.toString()+" and the area is: "+pol.area(-2, 5, 0.001));
			System.out.println("**************************************************");

			String string = "0.2*X^4+(0-1.5)*X^3+3*X^2+(0-X)+(0-5)"; // the right string for the polynom.
			Polynom polynom = new Polynom("0.2X^4-1.5X^3+3X^2-X-5");
			Graph.PrintMinMax(polynom, string, -2, 6);
			

	}
	
	
	
	

}
