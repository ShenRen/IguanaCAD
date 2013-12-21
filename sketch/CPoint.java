package sketch;
import common.*;
import java.util.ArrayList;
public class CPoint {

	public Float2 pos;
	public ArrayList<Constraint> constraints;
	public boolean fixed, locked;

	public CPoint (Float2 p){
		pos = p;
		constraints = new ArrayList<Constraint>();
		fixed = false;
		locked = false;
	}
}
