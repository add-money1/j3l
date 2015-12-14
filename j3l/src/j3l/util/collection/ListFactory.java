package j3l.util.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import j3l.util.check.ArgumentChecker;


/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2015.12.14_0
 * @author Johannes B. Latzel
 */
public final class ListFactory {
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static <T> List<T> createList(ListType list_type) {
		switch( ArgumentChecker.checkForNull(list_type, "list_type") ) {
			case ArrayList:
				return new ArrayList<>();
			case LinkedList:
				return new LinkedList<>();
			default:
				throw new IllegalArgumentException("Can not process the list_type: " + list_type.toString());
		}
	}
	
}
