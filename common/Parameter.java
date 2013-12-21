package common;

public class Parameter extends Variable {

	public ParameterizedObject parent;
	public Expr def, val;
	
	public Parameter (String name, Expr def, ParameterizedObject parent) {
		this.name = name;
		this.def = def;
		this.parent = parent;
		this.val = null;
	}

	public double eval () {
		if (val == null) {
			return def.eval();
		} else {
			return val.eval();
		}
	}


}
