package sketch;
public class Segment extends Primitive {

	public Segment (Point p1, Point p2) {
		super ();
		points.add (p1);
		points.add (p2);
	}

	public Segment (Point p1, Point p2, int id) {
		super (id);
		points.add (p1);
		points.add (p2);
	}

}
