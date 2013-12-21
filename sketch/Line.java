package sketch;
import common.*;

public class Line extends Shape2{

	public Float2 pt, dir;

	public Line (Float2 p, Float2 d){
		pt = p;
		dir = d.normalize();
	}

	public boolean equals (Object other) {
		if (other instanceof Line) {
			Line l = (Line) other;
			return (dir.equals(l.dir) || dir.equals(l.dir.mul(-1))) && Math.abs(Math.abs(l.pt.sub(pt).dot(dir)) - 1) < 1e-8;
		}
		return false;
	}

	public double getT (Float2 x) {
		x = x.sub(pt);
		return x.mag();	// since dir is normalized
	}

	public Shape2 intersection (Shape2 other){
		if (other == null) return null;
		if (other instanceof Line) {
			Line line = (Line) other;
			if (pt.dist(line.pt) < 1e-8) {	// the lines start at the same place.
				if (dir.equals(line.dir)) {	// they coincide
					return this;
				} else {
					return new Point(pt);
				}
			}
			if (Math.abs(Math.abs(dir.dot(line.dir)) - 1) > 1e-8) {	// non-parallel
				// This approach is based on the Law of Sines.
				double d = line.pt.dist(pt);
				double theta = dir.angle(line.dir);
				double phi = dir.angle(line.pt.sub(pt).normalize());
				double x = d * Math.sin(phi) / Math.sin(theta);
				Float2 D = pt.sub(line.pt).normalize();
				if (line.dir.dot (D) < 0) {
					x = -x;
				}
				return new Point (line.pt.add(line.dir.makeLength(x)));
			} else {	// lines are parallel.
				if ((Math.abs(line.pt.sub(pt).normalize().dot(dir)) - 1) < 1e-8) {
					// lines coincide
					return this;
				} else {
					return null;	// lines are disjoint
				}
			}
		} else if (other instanceof Circle) {
			Circle circ = (Circle) other;
			Line perp = new Line (circ.c, dir.perp());
			Point p = (Point) this.intersection (perp);
			double d = p.pos.dist(circ.c);
			if (d + 1e-8 < circ.r) {	// line intersects circle in two points
				double x = Math.sqrt (circ.r * circ.r - d*d);
				double pdist = p.pos.dist(pt);
				return new Union (new Point(pt.add(dir.makeLength(pdist-x))), new Point(pt.add(dir.makeLength(pdist+x))));
			} else if (d - 1e-8 > circ.r) {	// line misses circle
				return null;
			} else {	// line is tangent to circle
				return p;
			}
		} else {
			return other.intersection (this);
		}
	}
	
}
