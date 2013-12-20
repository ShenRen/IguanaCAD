package sketch.geom;
import common.*;

public class Point extends Shape2{

	public Float2 pos;

	public Point (Float2 p){
		pos = p;
	}

	public boolean equals (Object other) {
		if (other instanceof Point) {
			Point op = (Point) other;
			return op.pos.dist(pos) < 1e-8;
		}
		return false;
	}

	public Shape2 intersection (Shape2 other) {
		if (other == null) return null;
		if (other instanceof Point) {
			Point op = (Point) other;
			if (op.pos.dist(pos) < 1e-8) {
				return this;
			} else {
				return null;
			}
		} else if (other instanceof Line) {
			Line ol = (Line) other;
			if (pos.sub(ol.pt).normalize().sub(ol.dir).mag() < 1e-8) {
				return this;
			} else {
				return null;
			}
		} else if (other instanceof Circle) {
			Circle c = (Circle) other;
			if (Math.abs(c.c.dist(pos) - c.r) < 1e-8) {
				return this;
			} else {
				return null;
			}
		} else if (other instanceof Union) {
			Union u = (Union) other;
			for (Shape2 s : u.shapes) {
				if (intersection(s) != null) {
					return this;
				}
			}
		} else {
			return other.intersection (this);
		}
	}
}
