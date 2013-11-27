package common;
public class Float2 {

	public double x,y;

	public Float2 (double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Float2 () {
		x = 0;
		y = 0;
	}

	public Float2 add (Float2 other) {
		return new Float2 (x + other.x, y + other.y);
	}

	public Float2 sub (Float2 other) {
		return new Float2 (x - other.x, y - other.y);
	}

	public Float2 mul (double d){
		return new Float2 (x*d, y*d);
	}

	public Float2 mul (Float2 other) {
		return new Float2 (x*other.x, y*other.y);
	}

	public double dot (Float2 other) {
		return x*other.x + y*other.y;
	}

	public double mag () {
		return Math.sqrt (x*x + y*y);
	}

	public double magsq () {
		return x*x + y*y;
	}

	public Float2 normalize () {
		return mul(1/mag());
	}

	public double dist (Float2 a){
		return Math.sqrt ((x-a.x)*(x-a.x) + (y-a.y)*(y-a.y));
	}

	public String toString () {
		return "(" + x + ", " + y + ")";
	}

}
