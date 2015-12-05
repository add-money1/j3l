package j3l.util.collection.interfaces.remove;

import java.util.Collection;

/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2015.12.03_0
 * @author Johannes B. Latzel
 */
public interface IBasicRemove<T> {
	
	
	/**
	 * <p>
	 * </p>
	 *
	 * @param
	 * @return
	 */
	boolean remove(T element);
	
	
	/**
	 * <p>
	 * </p>
	 *
	 * @param
	 * @return
	 */
	default boolean removeAll(Collection<? extends T> collection) {
		boolean has_changed = false;
		for (T element : collection) {
			if (!has_changed) {
				has_changed = remove(element);
			} else {
				remove(element);
			}
		}
		return has_changed;
	}
	
	
}
