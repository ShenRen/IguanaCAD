package sketch;
import java.util.ArrayList;
import common.*;
public class Primitive {

	public int id;	// Globally, every piece of geometry gets assigned an ID, so that geometry that gets updated but is really just the same piece of thing gets the same ID (like if a dimension changes). 
	
	public UpdatingShape shape;

	public boolean guide;	// is it guide geometry?
	public boolean inloop;

	public Primitive (UpdatingShape s) {
		id = ID.getNew (this);
		shape = s;
		guide = false;
		inloop = false;
	}

	public Primitive (UpdatingShape s, int id) {
		this.id = id;
		shape = s;
		guide = false;
		inloop = false;
	}

	public boolean intersects (Primitive other) {
		return shape.intersection(other.shape) != null;
	}

}
