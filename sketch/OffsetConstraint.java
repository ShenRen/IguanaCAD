package sketch;
public class OffsetConstraint extends Constraint {

	// a fixed, absolute offset from another point: b.pos.sub(a.pos).equals(offset) should be true when the constraint is satisfied.
	
	public Point a, b;
	public Float2 offset;

	public OffsetConstraint (Point a, Point b, Float2 offset) {
		super (a,b);
		this.a = a;
		this.b = b;
		this.offset = offset;
	}

	public sketch.geom.Shape2 getViableSet (Point p) {
		if (p == a) {
			return new sketch.geom.Point (b.pos.sub(offset));
		} else if (p == b) {
			return new sketch.geom.Point (a.pos.add(offset));
		} else {
			System.err.println ("ERROR! Target point is neither constrained point in OffsetConstraint!");
			return null;
		}
	}
}
		
