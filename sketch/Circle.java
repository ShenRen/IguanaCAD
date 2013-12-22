package sketch;
import common.*;
public class Circle extends UpdatingShape {

	public Expr rad;

	public double r;
	public Float2 c;

	public Circle (Float2 c, double r){
		super (false);
		this.c = c;
		this.r = r;
	}

	public Circle (CPoint cent, Expr rad) {
		super (true);
		this.rad = rad;
		points.add (cent);
		update();
	}

	public String toString () {
		return "CIRCLE c = " + c + " r = " + r;
	}

	public void update () {
		if (usepoints) {
			c = points.get(0).pos;
			r = rad.eval();
		}
	}

	public boolean equals (Object other) {
		if (other instanceof Circle) {
			Circle oc = (Circle) other;
			return oc.c.dist(c) < 1e-8 && Math.abs(r-oc.r) < 1e-8;
		}
		return false;
	}

	public Shape2 intersection (Shape2 other) {
		if (other == null) return null;
		if (other instanceof Circle) {	
			Circle oc = (Circle) other;
			double cdist = oc.c.dist(c);
			if (cdist < 1e-8) {
				if (Math.abs(r - oc.r) < 1e-8) {	// overlapping circles
					return this;
				} else {
					return null;	// concentric circles with inequal radii
				}
			} else {
				if (r + oc.r + 1e-8 < cdist) {	// disjoint circles
					return null;
				} else if (r + oc.r - 1e-8 < cdist) {
					double rdiff = Math.max (r, oc.r) - Math.min(r, oc.r);
					if (cdist < rdiff - 1e-8) {	// one circle is contained in the other
						return null;
					} else if (cdist > rdiff + 1e-8) {// see mathworld.wolfram.com/Circle-CircleIntersection.html
						double d = cdist;
						double R = oc.r;
						double Q = (d*d - r*r + R*R);
						double x = Q/(2*d);
						double a = (0.5/d) * Math.sqrt (4*d*d*R*R - Q*Q);
						Float2 dir = c.sub(oc.c);
						Float2 cp = oc.c.add(dir.makeLength(x));
						Float2 spur = dir.perp().makeLength(a);
						return new Union (new Point(cp.add(spur)), new Point(cp.sub(spur)));
					} else {	// kissing circles (inside)
						return new Point(c.add(oc.c.sub(c)).makeLength(r));
					}
				} else {	// kissing circles (from the outside)
					return new Point(c.add(oc.c.sub(c)).makeLength(r));
				}
			}
		} else {
			return other.intersection (this);
		}
	}


}

