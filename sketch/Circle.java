package sketch;
import common.*;
import sketch.geom.Shape2;
public class Circle extends Primitive {

	public Expr rad;

	public Circle (Point c, Expr r) {
		super();
		points.add (c);
		rad = r;
	}

	public Circle (Point c, Expr r, int id) {
		super(id);
		points.add (c);
		rad = r;
	}

	public Shape2 getShape2 () {
		return new sketch.geom.Circle (points.get(0).pos, rad.eval());
	}
}
		

