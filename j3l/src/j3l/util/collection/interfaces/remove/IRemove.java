package j3l.util.collection.interfaces.remove;

import java.util.stream.Stream;

/**
 * <p>
 * </p>
 * 
 * @since JDK 1.8
 * @version 2015.12.03_0
 * @author Johannes B. Latzel
 */
public interface IRemove<T> extends IBasicRemove<T>, IExtendedRemove<T> {	
	
	
	/*
	 * (non-Javadoc)
	 * @see j3l.util.collection.IExtendedRemove#removeAll(java.util.stream.Stream)
	 */
	@Override default void removeAll(Stream<? extends T> stream) {
		stream.forEach(element -> remove(element));
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see j3l.util.collection.IExtendedRemove#removeAll(java.lang.Object[])
	 */
	@Override default boolean removeAll(T[] array) {
		boolean has_changed = false;
		for (T element : array) {
			if (!has_changed) {
				has_changed = remove(element);
			} else {
				remove(element);
			}
		}
		return has_changed;
	}
	
}
