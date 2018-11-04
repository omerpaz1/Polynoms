package myMath;

import java.util.Comparator;

/**
 * 
 * Know to compare two Monoms is able to say who is the biggest and if there are equal according to the powers
 *@author Omer Paz,Shimon Mimoun
 *
 */
public class Monom_Comperator implements Comparator<Monom> {
/**
 * Compare between two Monom according to the power
 * @param m1 Is the first Monom which I want to compare
 * @param m2 Is the Second Monom which I want to compare
 * 
 * @return if a power of m1 > power of m2 return -1 if a power of m1 < power of m2 return 1 if a power of m1 equals power of m2 return 0 
 */
	public int compare(Monom m1, Monom m2) {
		
		if(m1.get_power() > m2.get_power()) {
			return -1;
		}
		if (m1.get_power() < m2.get_power()) {
			return 1;
		}
		return 0;
		
	}

	// ******** add your code below *********

}
