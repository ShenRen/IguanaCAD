package sketch;
import common.*;
import sketch.geom.Shape2;

public class Arc extends Primitive {

	public Point c, p1, p2;
	public boolean cw;

	public Expr rad, theta;

	/* Note that it would be a good idea to verify that p1 and p2 are both the same distance from c before trying to actually render or do anything with this arc! (Appropriate constraints should be constructed) */

	public Arc (Point c, Point p1, Point p2, Expr r, Expr angle) {
		super ();
		this.c = c;
		this.p1 = p1;
		this.p2 = p2;
		this.rad = rad;
		this.theta = angle;
		this.cw = true;
		points.add (p1);
		points.add (p2);
		points.add (c);
	}

	public Arc (Point c, Point p1, Point p2, Expr r, Expr angle, int id) {
		super (id);
		this.c = c;
		this.p1 = p1;
		this.p2 = p2;
		this.rad = rad;
		this.theta = angle;
		this.cw = true;
		points.add (p1);
		points.add (p2);
		points.add (c);
	}

	public Shape2 getShape2 () {
		return new sketch.geom.Arc (c.pos, rad.eval());
	}
}
