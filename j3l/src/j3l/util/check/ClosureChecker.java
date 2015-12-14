package j3l.util.check;

import j3l.exception.ClosureException;
import j3l.util.close.IClose;


/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2015.12.13_0
 * @author Johannes B. Latzel
 */
public final class ClosureChecker {
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public final static <T extends Exception> IClose<T> checkForOpen(IClose<T> close) {
		return checkForOpen(close, close.toString());
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public final static <T extends Exception> IClose<T> checkForOpen(IClose<T> close, String name_of_the_instance) {
		if( !close.isOpen() ) {
			throw new ClosureException("The " + name_of_the_instance + " is not open!");
		}
		else {
			return close;
		}
	}
	
}
