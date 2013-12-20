package sketch;

public class Loop {	/* Represents a closed loop of geometry */

	/* After the sketch is solved, some work needs to be done to ready it for its 3D-instatntiation operations (extrude, revolve, sweep?, etc)
	 * 
	 * The sketch must be divided into a set of closed loops (consisting of line segments and arcs (or circles)). A loop must be a simple curve,
	 * but may have other loops inside it (which will make cutouts). The first step is then to find the loops in the sketch, and check for 
	 * self-intersections. 
	 *
	 * After the loops have been found, we must generate the appropriate CSG objects. For instance, extruded convex polygons can be represented
	 * as the intersection of half-spaces, and revolved arcs will be toroidal sections. However, concavities must be separately eliminated (by
	 * differencing operations). Note that this process can be recursive (that is, the concavity can have concavities, and so on). To determine
	 * what the concavities are, the convex hull will need to be determined. 
	 *
	 * The beauty of it is that the same exact procedure can be used to generate revolves and extrudes (and sweeps (after all, extrudes and
	 * revolves are just special cases of sweeps)), just with different actual objects generated - the same intersection + difference structure
	 * will apply. 
	 *
	 * Briefly:
	 *
	 * 0) Solve Sketch
	 * 1) Detect loops and check for self-intersection
	 * 2) Orient loops (so the half-spaces point the right direction)
	 * 3) Find convex hull and difference out concavities (possibly recursively)
	 *
	*/

	public ArrayList<Primitive> segs;

	public Loop () {
		segs = new ArrayList<Primitive>();
	}

	public boolean contains (Primitive p) {
		return segs.contains(p);
	}

	public void add (Primitive p) {
		segs.add (p);
	}

	public void orient () {
	}





}
