package j3l.util.check;

import java.util.function.Function;


/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2016.06.27_0
 * @author Johannes B. Latzel
 */
public final class ElementChecker {
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static boolean checkAllElementsForEquality(byte[] buffer1, byte[] buffer2) {
		if( buffer1.length != buffer2.length ) {
			return false;
		}
		for(int a=0,n=buffer1.length;a<n;a++) {
			if( buffer1[a] != buffer2[a] ) {
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
	public static boolean checkAllElementsForZero(byte[] array) {
		return ElementChecker.checkAllElements(array, (byte)0);
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
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public static <T> boolean checkAllElementsForNull(T[] array) {
		
		for( T element : array ) {
			if( element == null ) {
				return true;
			}
		}
		
		return true;
		
	}
	
	
}
