package sketch;
import common.*;
import java.util.ArrayList;
public class Point extends Primitive {

	public Float2 pos;
	public ArrayList<Constraint> constraints;
	public boolean fixed, locked;

	public Point (Float2 p){
		super ();
		pos = p;
		constraints = new ArrayList<Constraint>();
		fixed = false;
		locked = false;
		points.add (this);
	}

	public Point (Float2 p, int id) {
		super(id);
		pos = p;
		constraints = new ArrayList<Constraint> ();
		fixed = false;
		locked = false;
		points.add (this);
	}


}
