package sketch;
import java.util.ArrayList;
public abstract class Constraint {

	public ArrayList<Point> points;

	public Constraint (Point... list) {
		points = new ArrayList<Point> ();
		for (Point p : list) {
			points.add(p);
			p.constraints.add(this);	// leaking 'this' in the constructor is poor form, but really convenient here.
		}
	}

}
