package sketch;
import common.*;
import java.util.ArrayList;
public class Point extends Primitive {

	public Float2 pos;
	public ArrayList<Constraint> constraints;
	public boolean fixed;

	public Point (Float2 p){
		pos = p;
		constraints = new ArrayList<Constraint>();
		fixed = false;
	}


}
