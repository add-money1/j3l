package j3l.util;

import java.util.stream.Stream;


/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2015.12.03_0
 * @author Johannes B. Latzel
 */
public interface IExtendedAdd<T> {
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	void addAll(Stream<? extends T> stream);
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	boolean addAll(T[] array);
	
}
