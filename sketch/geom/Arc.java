package sketch.geom;
import common.*;

public class Arc {

	public Circle circ;
	public Float2 p1, p2;
	private Float2 n;

	public Arc (Float2 center, double rad, Float2 p1, Float2 p2, boolean flip) {
		circ = new Circle (center, rad);
		this.p1 = p1;
		this.p2 = p2;
		n = p2.sub(p1).perp().mul( flip ? -1 : 1 );
	}

	public boolean equals (Object other) {
		if (other instanceof Arc) {
			Arc a = (Arc) other;
			if (circ.equals(a.circ)) {
				if (Math.abs(start - a.start) < 1e-8 && Math.abs(stop - a.stop) < 1e-8) {
					return true;
				}
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

	}

	public Shape2 intersection (Shape2 other) {
		if (other == null) return null;
		Shape2 inter = circ.intersection (other);
		return prune (inter);
	}
}
