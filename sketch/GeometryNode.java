package sketch;
import java.util.ArrayList;

public class GeometryNode extends SNode {

	public ArrayList<Primitive> shapes;

	public GeometryNode (Primitive... s){
		shapes  = new ArrayList<Primitive>();
		for (Primitive p : s) {
			shapes.add(p);
		}
	}
}
