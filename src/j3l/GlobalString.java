package j3l;

import j3l.util.Checker;

/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2016.06.15_0
 * @author Johannes B. Latzel
 */
public enum GlobalString {
	
	LongRange("long_range"),
	Range("range");
	
	
	private final String string;
	
	
	private GlobalString(String string) {
		this.string = Checker.checkForEmptyString(string, "string");
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override public String toString() {
		return string;
	}
	
}
