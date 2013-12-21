package sketch;
import java.util.ArrayList;
public abstract class Constraint {

	public ArrayList<CPoint> points;

	public Constraint (CPoint... list) {
		points = new ArrayList<CPoint> ();
		for (CPoint p : list) {
			points.add(p);
			p.constraints.add(this);	// leaking 'this' in the constructor is poor form, but really convenient here.
		}
	}

	public abstract Shape2 getViableSet (CPoint p);	// get the shape that represents the set of potential locations for point p, when all of the other points are fixed at their current locations.

}
