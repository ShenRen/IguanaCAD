package sketch;
import java.util.ArrayList;
import common.*;
public class Sketch extends ParameterizedObject {

	public String name;
	public SNode root;
	public ArrayList<Measurement> 	meas;
	public ArrayList<Primitive> 	geom;	// the result of the solve.
	public ArrayList<Loop>		loops;
	
	public Sketch (String name) {
		super();
		this.name = name;
		meas = new ArrayList<Measurement>();
		geom = new ArrayList<Primitive> ();
		loops = new ArrayList<Loop> ();
		root = null;
	}



	public boolean isSelfIntersecting () {
		// check whether any of the primitives in geom intersect any others. 
		for (int i=0; i<geom.size(); i++) {
			for (int j=i+1; j<geom.size(); j++) {
				if (geom.get(i).intersects(geom.get(j))) {
					return true;
				}
			}
		}
		return false;
	}

	public int findPrimitiveWithPoint (Loop l, CPoint p) {	// pass the current primitive in, so we don't include it in the list.
		/* If there's exactly 1 thing not in the loop, it will return its index. */
		int ct = 0;
		int idx = -1;
		System.err.println ("I'ma find me a primitive wif ma point buddy, " + p);
		for (int i=0; i < geom.size(); i++) {
			Primitive x = geom.get(i);
			System.err.print ("\tchecking with i=" + i + ", " + x.shape);
			if (x.shape.points.contains(p)) {
				System.err.print (": it has the point");

				ct ++;
				if (!l.contains(x)) {
					System.err.print (", and it's not in the loop already!");
					idx = i;
				}
			}
			System.err.println();
		}
		if (ct == 2) {
			if (idx == -1) {
				return -2;
			}
			return idx;
		}
		// count should never be 0, since we should be calling this on a point that came from the loop, so we never risk confusing index 0 and count of 0.
		return -ct;
	}

	public void detectLoops () {
		/* Clear the inloop flags */
		for (Primitive p : geom) {
			p.inloop = false;
		}

		/* Do the actual detection work */
		int w = 0;

		if (geom.isEmpty ()) return;
		while (w < geom.size()) {
			if (geom.get(w).inloop){
				w++;
			} else {
				System.err.println("Beginning a new loop");
				Loop l = new Loop ();
				l.add (geom.get(w));
				if (geom.get(w).shape instanceof Circle) {
					w++;
					loops.add (l);
					continue;
				}
				int SLOWDOWN = 0;
				while (SLOWDOWN < 10) {
					SLOWDOWN ++;
					CPoint curr_end = l.segs.get(l.segs.size()-1).shape.points.get(1);
					System.err.println ("Trying to add something to connect to   " + curr_end);
					int idx = findPrimitiveWithPoint (l, curr_end);
					if (idx < 0) {
						if (idx == -2) {	// then there were still 2 things, but both were in the loop. This means we've come full circle.
							loops.add(l);
							w++;
							break;
						}
						System.err.println ("Nonmanifold loopy thing. Count value is " + (-idx));
						System.exit(1);
					} else {
						if (!l.contains(geom.get(idx))) {
							if (geom.get(idx).shape.points.get(1) == curr_end) {	// then we need to flip things around
								System.err.println ("Flipping order of points in list");
								geom.get(idx).shape.points.set (1, geom.get(idx).shape.points.get(0));
								geom.get(idx).shape.points.set (0, curr_end);
							}
							l.add (geom.get(idx));
							geom.get(idx).inloop = true;
						} else {	// I don't know what this means. Can this even happen? I thought we checked for this.
						}
					}
				}
			}
		}

		/* Orient the loops */
		for (Loop l : loops) {
			l.orient ();
		}
	}

	public void solve () {
		geom.clear();
		// solve the sketch now, putting the resulting geometry in geom. This will include created geometry like things from mirrors or patterns, and won't include things like guide geometry. It is exactly what should be rendered. 
		/* Things we need to think about:
		 * 
		 * 1) How & when which things get generated (with the nodes, etc)
		 * 2) What are we doing to keep track of things?
		 * 3) What about those parameters?

	}
}
