package sketch;
import java.util.ArrayList;
import sketch.geom.Shape2;
public abstract class Constraint {

	public ArrayList<Point> points;

	public Constraint (Point... list) {
		points = new ArrayList<Point> ();
		for (Point p : list) {
			points.add(p);
			p.constraints.add(this);	// leaking 'this' in the constructor is poor form, but really convenient here.
		}
	}

	public abstract Shape2 getViableSet (Point p);	// get the shape that represents the set of potential locations for point p, when all of the other points are fixed at their current locations.

}
