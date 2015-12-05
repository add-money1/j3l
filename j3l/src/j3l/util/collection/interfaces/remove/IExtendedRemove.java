package j3l.util.collection.interfaces.remove;

import java.util.stream.Stream;

/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2015.12.03_0
 * @author Johannes B. Latzel
 */
public interface IExtendedRemove<T> {
	
	
	/**
	 * <p>
	 * </p>
	 *
	 * @param
	 * @return
	 */
	void removeAll(Stream<? extends T> stream);
	
	
	/**
	 * <p>
	 * </p>
	 *
	 * @param
	 * @return
	 */
	boolean removeAll(T[] array);
	
}
