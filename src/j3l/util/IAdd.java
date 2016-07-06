package j3l.util;

import java.util.stream.Stream;

/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2015.12.03_0
 * @author Johannes B. Latzel
 */
public interface IAdd<T> extends IBasicAdd<T>, IExtendedAdd<T> {
		
	
	/*
	 * (non-Javadoc)
	 * @see j3l.util.collection.IExtendedAdd#addAll(java.util.stream.Stream)
	 */
	@Override default void addAll(Stream<? extends T> stream) {
		 stream.forEach(element -> add(element));
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see j3l.util.collection.IExtendedAdd#addAll(java.lang.Object[])
	 */
	@Override default boolean addAll(T[] array) {
		 boolean has_changed = false;
		 for( T element : array ) {
			 if( !has_changed ) {
				 has_changed = add(element);
			 }
			 else {
				 add(element);
			 }
		 }
		 return has_changed;
	}
	
}
