package j3l.util.stream;

import j3l.util.IValidate;


/**
 * <p>utility-class - provides filter-methods for {@link java.util.stream.Stream streams}</p>
 * 
 * @since JDK 1.8
 * @version 2015.12.14_0
 * @author Johannes B. Latzel
 */
public class StreamFilter {
	
	
	/**
	 * <p>filters null-references</p>
	 *
	 * @param t any reference
	 * @return true if not-null, else otherwise
	 */
	public static <T> boolean filterNull(T t) {
		return t != null;
	}
	
	
	/**
	 * <p>filters invalid instances</p>
	 *
	 * @param t any reference to an instance of {@link j3l.util.IValidate IValidate}
	 * @return true if the instance is valid, false otherwise
	 */
	public static <T extends IValidate> boolean filterInvalid(T t) {
		return StreamFilter.filterNull(t) && t.isValid();
	}
	
	
}
