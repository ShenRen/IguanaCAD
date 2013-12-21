package sketch;
import common.*;

public class DistanceConstraint extends Constraint { 

	public Expr dist;
	public CPoint a, b;	// these are going to be duplicated in the list, but that's ok.

	public DistanceConstraint (CPoint a, CPoint b, Expr d) {
		super (a, b);
		this.a = a;
		this.b = b;
		dist = d;
	}

	public Shape2 getViableSet (CPoint p) {
		CPoint c = p==a?b:a;	// the other point acts as the center of the circle.
		return new Circle (c.pos, dist.eval());
	}
}
