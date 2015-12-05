package j3l.util.close;

import j3l.util.ClosureState;

/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2015.10.29_0
 * @author Johannes B. Latzel
 */
public interface IStateClosure {
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	ClosureState getClosureState();
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public default boolean isInOpening() {
		return getClosureState().compareTo(ClosureState.InOpening) == 0;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public default boolean isOpen() {
		return getClosureState().compareTo(ClosureState.Open) == 0;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public default boolean isInClosure() {
		return getClosureState().compareTo(ClosureState.InClosure) == 0;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public default boolean isClosed() {
		return getClosureState().compareTo(ClosureState.Closed) == 0;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public default boolean hasBeenOpened() {
		return getClosureState().compareTo(ClosureState.Open) >= 0;
	}
	
}
