package sketch;
import java.util.ArrayList;
public abstract class Primitive {

	public int id;	// Globally, every piece of geometry gets assigned an ID, so that geometry that gets updated but is really just the same piece of thing gets the same ID (like if a dimension changes). 

	public ArrayList<Point> points;	// all primitives depend on some points to define them. 
	public boolean guide;	// is it guide geometry?

	public Primitive () {
		id = ID.getNew (this);
		points = new ArrayList<Point> ();
		guide = false;
	}

	public Primitive (int id) {
		this.id = id;
		guide = false;
		points = new ArrayList<Point> ();
	}

	private static int CURR_ID = 0;

	public static int getNewID () {
		return CURR_ID++;
	}

}
