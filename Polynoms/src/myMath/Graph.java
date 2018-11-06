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
		
		//String s = "0.2*X^4+(0-1.5)*X^3+3*X^2+(0-X)+(0-5)";
		String s = "X^3+(0-2*X)";
		

		Polynom poly = new Polynom("X^3-2X");
		System.out.println(poly.getMax());
		
		//poly.underX_area(-2, 6, 0.01);
		poly.maxMin_Polynom(-2, 2, 0.001);
		
		Draw_Polynom(s);
	}
	// ex1
	
	public static void Draw_Polynom(String s) {
		JFrame f = new JFrame();		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		OptionAxe optionsAxes = new OptionAxe(Color.BLACK, true, -1, 1, true, true);
		Graphique.getInstance().initGraphique(new AxeX(-2, 10, optionsAxes), new AxeY(-10, 6, optionsAxes));
		Graphique.getInstance().ajouterElement(new Point('A', -0.81600000000011, 1.0886615040000003));
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
