package common;

public class ID {

	private static int CURR_ID = 0;
	private static HashMap<Integer, Object> objs = new HashMap<Integer, Object> ();

	public static int getNew (Object obj) {
		objs.put(CURR_ID, obj);
		return CURR_ID++;
	}

	public static void freeID (int id) {
		objs.remove (id);
	}

	public static Object get (int id) {
		return objs.get(id);
	}

}



