package sketch;
import java.util.ArrayList;
import common.*;
import sketch.geom.Shape2;
public abstract class Primitive {

	public int id;	// Globally, every piece of geometry gets assigned an ID, so that geometry that gets updated but is really just the same piece of thing gets the same ID (like if a dimension changes). 

	public ArrayList<Point> points;	// all primitives depend on some points to define them. 
	public boolean guide;	// is it guide geometry?
	public boolean inloop;

	public Primitive () {
		id = ID.getNew (this);
		points = new ArrayList<Point> ();
		guide = false;
		inloop = false;
	}

	public Primitive (int id) {
		this.id = id;
		guide = false;
		inloop = false;
		points = new ArrayList<Point> ();
	}

	public abstract Shape2 getShape2 ();	// this will get a Shape2 that _contains_ this primitive. Does not have to match exactly. Ex: a line could be given for a segment, a circle for an arc. 

	public boolean intersects (Primitive other) {
		return getShape2().intersection(other.getShape2()) != null;
	}

}
