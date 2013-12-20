package common;

public class Expr2 {

	public Expr x, y;

	public Expr2 (Expr x, Expr y) {
		this.x = x;
		this.y = y;
	}

	public Expr2 (Float2 a) {
		this.x = new Expr (a.x);
		this.y = new Expr (a.y);
	}
}
