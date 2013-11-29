package sketch;
import common.*;
public class Circle extends Primitive {

	public Expr rad;

	public Cirlce (Point c, Expr r) {
		super();
		points.add (c);
		rad = r;
	}

	public Circle (Point c, Expr r, int id) {
		super(id);
		points.add (c);
		rad = r;
	}
}
		

