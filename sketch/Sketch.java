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

	public void solve () {
		geom.clear();
		// solve the sketch now, putting the resulting geometry in geom. This will include created geometry like things from mirrors or patterns, and won't include things like guide geometry. It is exactly what should be rendered. 
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
		for (int i=0; i < geom.size(); i++) {
			Primitive x = geom.get(i);
			if (x.shape.points.contains(p)) {
				ct ++;
				if (!l.contains(x)) {
					idx = i;
				}
			}
		}
		if (ct == 2) return idx;
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
				Loop l = new Loop ();
				l.add (geom.get(w));
				if (geom.get(w).shape instanceof Circle) {
					w++;
					loops.add (l);
					continue;
				}
				while (true) {
					CPoint curr_end = l.segs.get(l.segs.size()-1).shape.points.get(1);
					int idx = findPrimitiveWithPoint (l, curr_end);
					if (idx < 0) {
						System.err.println ("Nonmanifold loopy thing. Count value is " + (-idx));
						System.exit(1);
					} else {
						if (!l.contains(geom.get(idx))) {
							if (geom.get(idx).shape.points.get(1) == curr_end) {	// then we need to flip things around
								geom.get(idx).shape.points.set (1, geom.get(idx).shape.points.get(0));
								geom.get(idx).shape.points.set (0, curr_end);
							}
							l.add (geom.get(idx));
							geom.get(idx).inloop = true;
						} else {	// then we've come back to some point in the loop. This could be a flower, though, so we should check.
							if (geom.get(idx) == l.segs.get(0)) {	// then it's a real loop.
								loops.add (l);
								break;
							} else {
								System.err.println ("Flower");
								System.exit(1);
							}
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

}
