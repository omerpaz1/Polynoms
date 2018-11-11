package myMath;

import java.awt.Color;
import java.util.ArrayList;
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

/**
 * This source makes it possible to graph all the usual functions by defining the representation window as well as a
 * large number of geometric objects: polygons, points, segments, circles ...
 * @author Omer Paz and Shimon Mimoun and Julien_c (Import).
 */
public class Graph {

	/**
	 * Main function used to perform the print sample
	 * @throws wrongDataException Exception that knows how to handle the String received by Polynom is check if the enter is correct
	 */
	public static void main(String[] args) throws wrongDataException {

		String string = "0.2*X^4+(0-1.5)*X^3+3*X^2+(0-X)+(0-5)"; // the right string for the polynom.
		Polynom poly = new Polynom("0.2X^4-1.5X^3+3X^2-X-5");
		System.out.println(poly.toString());
		PrintMinMax(poly,string, -2,6);


	}
	/**
	 *The function is used the project Import Graph, and call the function that can print a function and the Max and Min points.
	 * @param poly receiv Polynom for example: 0.2X^4-1.5X^3+3X^2-X-5 
	 * @param s  Receiv String in the form of a Polynom, expample of right string for Printing: 0.2*X^4+(0-1.5)*X^3+3*X^2+(0-X)+(0-5). 
	 * @param x0 lower definition domain
	 * @param x1 higher definition domain
	 */
	public static void PrintMinMax(Polynom poly, String s, double x0, double x1) {

		double start = x0;
		double end = x1;


		Polynom der = (Polynom) poly.derivative();
		double eps = 0.0001;
		for (;start < end ; start=start+eps) {
			if(der.f(start) < 0 && der.f(start+eps) > 0) {
				poly.Point_list.add(new Point(start,poly.f(start)));
			}
			if(der.f(start) > 0 && der.f(start+eps) < 0) {
				poly.Point_list.add(new Point(start,poly.f(start)));
			}

		}
		Draw_MinMax(s,poly.Point_list,x0,x1);

	}
	/**
	 * The function is used the project Import Graph, and print a function and the Max and Min points
	 * @param s  Receiv String in the form of a Polynom, expample of right string for Printing: 0.2*X^4+(0-1.5)*X^3+3*X^2+(0-X)+(0-5). 
	 * @param point_list Receiv ArrayList with Points Max and Min 
	 * @param x0 lower definition domain
	 * @param x1 higher definition domain
	 */
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




}
