package common;

public class Float3 {

	public double x,y,z;

	public Float3 (double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Float3 () {
		x = 0;
		y = 0;
		z = 0;
	}

	public Float3 add (Float3 other) {
		return new Float3 (x + other.x, y + other.y, z + other.z);
	}

	public Float3 sub (Float3 other) {
		return new Float3 (x - other.x, y - other.y, z - other.z);
	}

	public Float3 mul (double d){
		return new Float3 (x*d, y*d, z*d);
	}

	public Float3 mul (Float3 other) {
		return new Float3 (x*other.x, y*other.y, z*other.z);
	}

	public double dot (Float3 other) {
		return x*other.x + y*other.y + z*other.z;
	}

	public double mag () {
		return Math.sqrt (x*x + y*y + z*z);
	}

	public double magsq () {
		return x*x + y*y + z*z;
	}

	public Float3 normalize () {
		return mul (1/mag());
	}

	public double dist (Float3 a){
		return Math.sqrt ((x-a.x)*(x-a.x) + (y-a.y)*(y-a.y) + (z-a.z)*(z-a.z));
	}

	public Float3 cross (Float3 a){
		return new Float3 (y*a.z - a.y*z, z*a.x - a.z*x, x*a.y - a.x*y);
	}

	public Float3 rotateX (double theta) {
		double cos = Math.cos(theta);
		double sin = Math.sin(theta);
		return new Float3 (x, cos*y - sin*z, sin*y + cos*z);
	}

	public Float3 rotateY (double theta) {
		double cos = Math.cos(theta);
		double sin = Math.sin(theta);
		return new Float3 (cos*x - sin*z, y, sin*x + cos*z);
	}

	public Float3 rotateZ (double theta) {
		double cos = Math.cos(theta);
		double sin = Math.sin(theta);
		return new Float3 (cos*x - sin*y, sin*x + cos*y, z);
	}

	public Float3 axisRotate (Float3 axis, double theta) {
		double u = axis.x;
		double v = axis.y;
		double w = axis.z;
		double sin = Math.sin(theta);
		double cos = Math.cos(theta);
		double Q = (u*x + v*y + w*z) * (1-cos);
		double rx = u*Q + x*cos + (v*z - w*y)*sin;
		double ry = v*Q + y*cos + (w*x - u*z)*sin;
		double rz = w*Q + z*cos + (u*y - v*x)*sin;
		return new Float3 (rx, ry, rz);
	}

	public int getRGB () {
		return 256*256*((int) (x*255)) + 256*((int) (y*255)) + (int) (z*255);
	}

	public String toString () {
		return "(" + x + ", " + y + ", " + z + ")";
	}


}
