package j3l.util.close;


/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2015.11.29_0
 * @author Johannes B. Latzel
 */
public interface IClose<T extends Exception> extends AutoCloseable, IStateClosure {
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 * @throws T
	 */
	void open() throws T;
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	void close() throws T;
	
}
