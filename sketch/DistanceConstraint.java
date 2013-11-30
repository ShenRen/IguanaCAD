package sketch;
import common.*;

public class DistanceConstraint extends Constraint { 

	public Expr dist;
	public Point a, b;	// these are going to be duplicated in the list, but that's ok.

	public DistanceConstraint (Point a, Point b, Expr d) {
		super (a, b);
		this.a = a;
		this.b = b;
		dist = d;
	}

	public sketch.geom.Shape2 getViableSet (Point p) {
		Point c = p==a?b:a;	// the other point acts as the center of the circle.
		return new sketch.geom.Circle (c.pos, dist.eval());
	}
}
