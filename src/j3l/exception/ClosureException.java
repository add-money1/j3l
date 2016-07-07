package j3l.exception;

/**
 * <p></p>
 * 
 * @since JDK 1.8
 * @version 2015.11.16_0
 * @author Johannes B. Latzel
 */
public class ClosureException extends RuntimeException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7651031693885220555L;
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public ClosureException(String message) {
		this(message, null);
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public ClosureException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
