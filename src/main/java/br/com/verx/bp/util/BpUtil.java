package br.com.verx.bp.util;

public class BpUtil {

	public static boolean isNull(Object o) {
		if (o == null) return true;
		return false;
	}

	public static boolean isNullOrEmpty(Object o) {
		if (!isNull(o)) {
			if (o instanceof String) return ((String) o).isEmpty();
		}
		return isNull(o);
	}
}
