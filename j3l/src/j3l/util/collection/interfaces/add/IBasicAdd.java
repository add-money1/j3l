package j3l.util.collection.interfaces.add;

import java.util.Collection;

/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2015.12.03_0
 * @author Johannes B. Latzel
 */
public interface IBasicAdd<T> {
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	 boolean add(T element);
	 
	 
	 /**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	 default boolean addAll(Collection<? extends T> collection) {
		 boolean has_changed = false;
		 for( T element : collection ) {
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
