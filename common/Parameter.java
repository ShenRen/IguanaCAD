package common;

public class Parameter extends Variable {

	public ParameterizedObject parent;
	public double def;
	public double val;
	
	public Parameter (String name, double def, ParameterizedObject parent) {
		this.name = name;
		this.def = def;
		this.parent = parent;
		this.val = def;
	}

	public double eval () {
		return val;
	}
}
