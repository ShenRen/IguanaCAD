package sketch.geom;
import java.util.ArrayList;
import common.*;

public class Union extends Shape2 {

	public ArrayList <Shape2> shapes;

	public Union () {
		shapes = new ArrayList<Shape2>();
	}

	public Union (Shape2... sh) {
		this();
		for (Shape2 s : sh) {
			if (s instanceof Union) {	// flatten nested Unions (this only goes one level, though).
				Union su = (Union) s;
				for (Shape2 elem : su.shapes) {
					shapes.add(elem);
				}
			}
			shapes.add(s);
		}
		remove_duplicates ();
	}

	public void add (Shape2 s) {
		if (s == null) return;
		if (s instanceof Union) {
			for (Shape2 elem : ((Union) s).shapes) {
				shapes.add(elem);
			}
		} else {
			shapes.add(s);
		}
	}

	public void remove_duplicates () {
		for (int i=0; i<shapes.size(); i++){
			for (int j=shapes.size()-1; j > i; j--){
				if (shapes.get(i).equals(shapes.get(j))){
					shapes.remove(j);
				}
			}
		}
	}

	public Shape2 intersection (Shape2 other) {
		if (other instanceof Point) {	// already done
			return other.intersection (this);
		} else {
			Union res = new Union ();
			for (Shape2 s : shapes) {
				res.add(s.intersection (other));	// this works even if other is also a Union
			}
			res.remove_duplicates();
			if (res.shapes.isEmpty()){
				return null;
			} else if (res.shapes.size() == 1){
				return res.shapes.get(0);
			}
			return res;
		}
	}

}
