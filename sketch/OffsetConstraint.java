package sketch;
import common.*;
public class OffsetConstraint extends Constraint {

	// a fixed, absolute offset from another point: b.pos.sub(a.pos).equals(offset) should be true when the constraint is satisfied.
	
	public CPoint a, b;
	public Expr xoff, yoff;

	public OffsetConstraint (CPoint a, CPoint b, Expr x, Expr y) {
		super (a,b);
		this.a = a;
		this.b = b;
		xoff = x;
		yoff = y;
	}

	public Shape2 getViableSet (CPoint p) {
		Float2 offset = new Float2(xoff.eval(), yoff.eval());
		if (p == a) {
			return new Point (b.pos.sub(offset));
		} else if (p == b) {
			return new Point (a.pos.add(offset));
		} else {
			System.err.println ("ERROR! Target point is neither constrained point in OffsetConstraint!");
			return null;
		}
	}
}
		
