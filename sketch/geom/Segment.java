package sketch.geom;
import common.*;

public class Segment {

	public Line line;
	public double  tmax;

	public Segment (Float2 p1, Float2 p2) {
		Float2 dir = p2.sub(p1);
		line = new Line (p1, dir);
		tmax = dir.mag();
	}

	private Shape2 prune (Shape2 other) {
		if (other instanceof Point) {
			double t = line.getT (((Point) other).pos);
			if (t >= 0 && t <= tmax) return other;
			return null;
		} else if (other instanceof Line) {
			return this;
		} else if (other instanceof Segment) {
			Segment s = (Segment) other;
			double otmin = line.getT (s.line.pt) - 1e-8;
			double otmax = line.getT (s.line.pt.add(dir.mul(s.tmax))) + 1e-8;
			double min = Math.max (otmin, 0);
			double max = Math.min (otmax, tmax);
			if (min > max) return null;
			if (Math.abs(max - min) < 1e-8) {
				return new Point (line.pt.add (line.dir.mul(min)));
			} else {
				return new Segment (line.pt.add(line.dir.mul(min)), line.pt.add(line.dir.mul(max)));
			}
		} else if (other instanceof Union) {
			/* In this branch, there's nothing Segment-specific */
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
		Shape2 inter = line.intersection (other);
		return prune (inter);
	}
}

