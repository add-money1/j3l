package j3l.util.transform;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2015.11.19_0
 * @author Johannes B. Latzel
 */
public final class TransformStream {
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static <T> void addToCollection(Stream<T> stream, Collection<T> collection) {
		stream.forEach(element -> collection.add(element));		
	}
	
	
}
