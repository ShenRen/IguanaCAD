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

	public sketch.geom.Shape2 getShape2 () {
		return new sketch.geom.Line (points.get(0).pos, points.get(1).pos.sub(points.get(0).pos));
	}
}
