package sketch;
import common.*;
public class AngleConstraint extends Constraint {

	public CPoint a1, a2, b1, b2;
	public Expr theta;

	public AngleConstraint (CPoint a1, CPoint a2, CPoint b1, CPoint b2, Expr theta){
		super (a1, a2, b1, b2);
		this.a1 = a1;
		this.a2 = a2;
		this.b1 = b1;
		this.b2 = b2;
		this.theta = theta;
	}

	public Shape2 getViableSet (CPoint p) {
		Float2 dir = new Float2 ();
		if (p == a1 || p == a2) {
			dir = b2.pos.sub(b1.pos);
		} else {
			dir = a2.pos.sub(a1.pos);
		}
		Float2 dir1 = dir.rotate(theta.eval());
		Float2 dir2 = dir.rotate(-theta.eval());
		Float2 start = null;
		if (p == a1) {
			start = a2.pos;
		} else if (p == a2) {
			start = a1.pos;
		} else if (p == b1) {
			start = b2.pos;
		} else if (p == b2) {
			start = b1.pos;
		}
		Union u = new Union (new Line (start, dir1), new Line (start, dir2));	// this does duplicate removal, so parallel and perpendicualar lines work out nicely.
		if (u.shapes.size() == 1) {
			return u.shapes.get(0);	// remove the wrapper if we only have one thing.
		}
		return u;
	}
}
