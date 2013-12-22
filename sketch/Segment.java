package sketch;
import common.*;
public class Segment extends UpdatingShape {

	private Line line;
	private double  tmax;

	public Segment (CPoint p1, CPoint p2) {
		super (true);
		points.add (p1);
		points.add (p2);
		update ();
	}

	public Segment (Float2 p1, Float2 p2) {
		super (false);
		line = new Line (p1, p2.sub(p1));
		tmax = p2.sub(p1).mag();
	}

	public String toString () {
		return "SEGMENT (" + line + ") tmax = " + tmax;
	}

	public void update () {
		if (usepoints) {
			Float2 dir = points.get(1).pos.sub(points.get(0).pos);
			line = new Line (points.get(0).pos, dir);
			tmax = dir.mag();
		}
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
			double otmax = line.getT (s.line.pt.add(line.dir.mul(s.tmax))) + 1e-8;
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
		return null;	// Shouldn't be here

	}

	public Shape2 intersection (Shape2 other) {
		if (other == null) return null;
		Shape2 inter = line.intersection (other);
		return prune (inter);
	}
}
