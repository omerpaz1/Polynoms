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

public class Graph {
	
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
