package myMath;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import fr.julien.graphique.Graphique;
import fr.julien.graphique.ZoneGraphique;
import fr.julien.graphique.axes.AxeX;
import fr.julien.graphique.axes.AxeY;
import fr.julien.graphique.axes.OptionAxe;
import fr.julien.graphique.element.fonction.Fonction;
import fr.julien.graphique.element.forme.Polygone;
import fr.julien.graphique.element.point.Point;
import fr.julien.graphique.element.quadrillage.Quadrillage;

public class Graph {
	
	public static void main(String[] args) {
		
		String s = "0.2*X^4+(0-1.5)*X^3+3*X^2+(0-X)+(0-5)";
		//String s = "0.2X^4-1.5X^3+3X^2-X-5";
		Polynom poly = new Polynom("0.2X^4-1.5X^3+3X^2-X-5");
		//Polynom poly = new Polynom("4.8X-9");
		Polynom dev = (Polynom) poly.derivative();
		
		//poly.findf();
		
		poly.maxMin_Polynom(s, poly, -2, 6);
		//System.out.println("the area above x-ais: "+poly.underX_area(-2, 6, 0.01));
		//System.out.println("the area under x-ais: "+poly.area(-2, 6, 0.01));
		//System.out.println(poly.derivative());
		//Polynom p1 = new Polynom("X^2-1");
		//System.out.println(p1.area(0, 2, 0.01));
		//System.out.println(poly.root(-2, 6, 0.01));


		
		//Draw_Polynom(s, max_point , min_point);
	}
	// ex1
	


}
