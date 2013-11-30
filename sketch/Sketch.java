package sketch;
import java.util.ArrayList;
import common.*;
public class Sketch extends ParameterizedObject {

	public String name;
	public SNode root;
	public ArrayList<Measurement> meas;
	
	public Sketch (String name) {
		super();
		this.name = name;
		meas = new ArrayList<Measurement>();
		root = null;
	}



}
