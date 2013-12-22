package sketch;
import common.*;

public class Arc extends UpdatingShape {

	private Circle circ;
	private Float2 p1, p2;
	private Float2 n;
	private boolean flip;

	public Expr rad;

	public Arc (Float2 center, double r, Float2 p1, Float2 p2, boolean flip) {
		super (false);
		this.circ = new Circle (center, r);
		this.p1 = p1;
		this.p2 = p2;
		this.flip = flip;
		calcNormal ();
	}

	public Arc (CPoint cent, CPoint p1, CPoint p2, Expr rad, boolean flip) {
		super (true);
		points.add (p1);
		points.add (p2);
		points.add (cent);
		this.rad = rad;
		update();
	}

	public String toString () {
		return "ARC (" + circ + ") p1 = " + p1 + " p2 = " + p2 + (flip?" [flip] ":"");
	}

	public void flip () {
		flip = !flip;
		calcNormal ();
	}

	public void update () {
		if (usepoints) {
			circ = new Circle (points.get(2).pos, rad.eval());
			p1 = points.get(0).pos;
			p2 = points.get(1).pos;
			calcNormal ();
		}
	}

	private void calcNormal () {
		n = p2.sub(p1).perp().mul( flip ? -1 : 1 );
	}

	public boolean equals (Object other) {
		if (other instanceof Arc) {
			Arc a = (Arc) other;
			if (circ.equals(a.circ)) {
				if (flip == a.flip && p1.sub(a.p1).mag() < 1e-8 && p2.sub(a.p2).mag() < 1e-8) return true;
				if (flip != a.flip && p2.sub(a.p1).mag() < 1e-8 && p1.sub(a.p2).mag() < 1e-8) return true;
			}
		}
		return false;
	}

	public boolean onArc (Float2 p) {
		return p.sub(p1).dot(n) >= 0;
	}

	private Shape2 prune (Shape2 other) {	// assumption: other is the intersection of some object with this object's circle.
		if (other instanceof Point) {
			return onArc (((Point) other).pos) ? other : null;
		} else if (other instanceof Circle) {
			// then the circle MUST be equal to circ, and so the intersection will  be the entire arc.
			return this;
		} else if (other instanceof Arc) {
			// this must be an arc of the same circle
			Arc a = (Arc) other;
			

		} else if (other instanceof Union) {
			Union res = new Union ();
			Union u = (Union) other;
			for (Shape2 s : u.shapes) {
				Shape2 pr = prune (s);
				if (pr != null) {
					res.shapes.add (pr);
				}
			}
			if (res.shapes.size() == 0) {
				return null;
			} else if (res.shapes.size() == 1) {
				return res.shapes.get(0);
			} else {
				return res;
			}
		}
		return null;	// shouldn't be here
	}

	public Shape2 intersection (Shape2 other) {
		if (other == null) return null;
		Shape2 inter = circ.intersection (other);
		return prune (inter);
	}
}
