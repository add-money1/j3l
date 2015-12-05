package j3l.util.check;

import java.util.function.Function;

/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2015.11.25_0
 * @author Johannes B. Latzel
 */
public final class ElementChecker {
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static boolean checkAllElements(byte[] array, byte value) {
		
		for( byte b : array ) {
			if( b != value ) {
				return false;
			}
		}
		
		return true;
		
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static boolean checkAllElements(byte[] array) {
		
		for( byte b : array ) {
			if( b != 0 ) {
				return false;
			}
		}
		
		return true;
		
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static <T, R> boolean checkAllElements(T[] array, Function<T, R> function, T check_object) {
		
		for( T element : array ) {
			if( !function.apply(element).equals(check_object) ) {
				return false;
			}
		}
		
		return true;
		
	}
	
	
}
