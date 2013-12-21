package sketch;
import java.util.ArrayList;

public abstract class UpdatingShape extends Shape2 {

	public ArrayList<CPoint> points;
	boolean usepoints;

	public UpdatingShape (boolean use) {
		usepoints = use;
		if (use) {
			points = new ArrayList<CPoint> ();
		}
	}

	public abstract void update ();

}

