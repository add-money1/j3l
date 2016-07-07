package j3l.exception;

/**
 * <p>thrown when an exceptional event during or after a checksum-calculation occurs</p>
 * 
 * @since JDK 1.8
 * @version 2015.08.30_0
 * @author Johannes B. Latzel
 */
public final class ChecksumException extends Exception {

	/**
	 * <p>auto-generated serialVersionUID</p>
	 */
	private static final long serialVersionUID = 6952955541600965052L;

	
	/**
	 * <p>just calls super(message)</p>
	 * 
	 * @param message the message
	 */
	public ChecksumException(String message) {
		super(message);
	}
	
}
