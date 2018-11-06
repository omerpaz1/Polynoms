package fr.julien.graphique.lancement;

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
import fr.julien.graphique.element.segment.Segment;

public class Lanceur {

	public static void main(String[] args) {
		JFrame f = new JFrame();		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		OptionAxe optionsAxes = new OptionAxe(Color.BLACK, true, -1, 1, true, true);
		Graphique.getInstance().initGraphique(new AxeX(-2, 10, optionsAxes), new AxeY(-10, 6, optionsAxes));
		Graphique.getInstance().ajouterElement(new Point('A', 2, 2));
		Graphique.getInstance().ajouterElement(new Fonction(("0.2*X^4+(0-1.5)*X^3+3*X^2+(0-X)+(0-5)")));
//		Graphique.getInstance().ajouterElement(new Segment(new Point(1, 2), new Point(3, 3)));
		List<Point> points = new ArrayList<Point>();
//		points.add(new Point(1, 1)); 0.2*X^4+(0-1.5)*X^3+3*X^2+(0-X)+(0-5)
//		points.add(new Point(2, 2));
//		points.add(new Point(2, 1));
		Graphique.getInstance().ajouterElement(new Polygone(points));
		Graphique.getInstance().ajouterElement(new Quadrillage(0.5, 0.5));
		f.add(new ZoneGraphique());
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

}
