package j3l.util;

/**
 * <p>converts stacktraces to Strings</p>
 * 
 * @since JDK 1.8
 * @version 2016.05.16_0
 * @author Johannes B. Latzel
 */
public final class StackTraceConverter {
	
	
	/**
	 * <p>Just for performance so that ArgumentChecker doesn't always receives a new {@link java.lang.String String}</p>
	 */
	private final static String THREAD = "thread";
	
	
	/**
	 * <p>converts the trace of the current stack to a {@link java.lang.String String}</p>
	 *
	 * @return the stack trace - every {@link java.lang.StackTraceElement StackTraceElement} has its own line
	 */
	public static String getStackTrace() {
		return getStackTrace(Thread.currentThread());
	}
	
	
	/**
	 * <p>converts the trace of the given stack to a {@link java.lang.String String}</p>
	 *
	 * @return the stack trace - every {@link java.lang.StackTraceElement StackTraceElement} has its own line
	 * @throws java.lang.NullPointerException if the thread is equal to null
	 */
	public static String getStackTrace(Thread thread) {
		Checker.checkForNull(thread, THREAD);
		StackTraceElement[] stack_trace_array = thread.getStackTrace();
		StringBuilder stack_trace = new StringBuilder(stack_trace_array.length * 50);
		for(int a=0;a<stack_trace_array.length;a++) {
			stack_trace.append(stack_trace_array[a].toString());
			if( a != stack_trace_array.length - 1 ) {
				stack_trace.append('\n');
			}
		}
		return stack_trace.toString();
	}
	
}
