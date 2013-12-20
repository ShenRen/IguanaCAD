package sketch;
import common.*;
public class OffsetConstraint extends Constraint {

	// a fixed, absolute offset from another point: b.pos.sub(a.pos).equals(offset) should be true when the constraint is satisfied.
	
	public Point a, b;
	public Expr xoff, yoff;

	public OffsetConstraint (Point a, Point b, Expr x, Expr y) {
		super (a,b);
		this.a = a;
		this.b = b;
		xoff = x;
		yoff = y;
	}

	public sketch.geom.Shape2 getViableSet (Point p) {
		Float2 offset = new Float2(xoff.eval(), yoff.eval());
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
		
