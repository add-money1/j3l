package j3l.exception;

import java.nio.BufferOverflowException;

public class ValueOverflowException extends BufferOverflowException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7303532413502368163L;
	
	
	/**
	 * <p></p>
	 */
	private final Throwable cause;
	
	
	/**
	 * <p></p>
	 */
	private final String message;
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public ValueOverflowException(String message) {
		this(message, null);
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	public ValueOverflowException(String message, Throwable cause) {
		super();
		
		this.cause = cause;
		this.message = message;
	}
	
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Throwable#getCause()
	 */
	@Override public Throwable getCause() {
		return cause;
	}
	
	
	/**
	 * <p></p>
	 *
	 * @param
	 * @return
	 */
	@Override public String getMessage() {
		return message;
	}

}
