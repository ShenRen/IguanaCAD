package common;

public class Measurement extends Variable {

	public int id1, id2;

	public Measurement (String name, int id1, int id2) {
		this.name = name;
		this.id1 = id1;
		this.id2 = id2;
	}

	public double eval () {
		Object a = ID.get(id1);
		Object b = ID.get(id2);
		if (a instanceof sketch.Point && b instanceof sketch.Point) {
			return ((sketch.Point) a).pos.dist(((sketch.Point) b).pos);
		} else {
			System.err.println("BAD OBJECT TYPES IN Measurement.eval! ids are " + id1 + " and " + id2);
			return 0;
		}
	}
}

